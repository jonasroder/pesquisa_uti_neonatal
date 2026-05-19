package com.roderly.pesquisaneonatos.login.controller;

import com.roderly.pesquisaneonatos.infra.security.TokenService;
import com.roderly.pesquisaneonatos.login.dto.request.Authentication;
import com.roderly.pesquisaneonatos.login.dto.response.LoginResponse;
import com.roderly.pesquisaneonatos.login.model.RefreshToken;
import com.roderly.pesquisaneonatos.login.service.LoginLogService;
import com.roderly.pesquisaneonatos.login.service.RefreshTokenService;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private LoginLogService loginLogService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid Authentication data, HttpServletRequest request) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var usuario = (Usuario) auth.getPrincipal();

            String accessToken = tokenService.generateToken(usuario);
            String refreshTokenValue = refreshTokenService.createRefreshToken(usuario, data.rememberMe());

            loginLogService.registrarLog(usuario, request, "SUCCESS", null);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, refreshTokenService.buildCookie(refreshTokenValue, data.rememberMe()).toString())
                    .body(new LoginResponse(accessToken));

        } catch (BadCredentialsException e) {
            loginLogService.registrarLog(null, request, "FAILURE", "Usuário ou senha inválidos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        } catch (Exception e) {
            loginLogService.registrarLog(null, request, "FAILURE", "Erro interno no servidor.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar a solicitação.");
        }
    }


    @PostMapping("/renew")
    public ResponseEntity<?> renew(HttpServletRequest request) {
        String cookieToken = refreshTokenService.extractFromCookie(request);

        if (cookieToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token ausente.");
        }

        try {
            RefreshToken refreshToken = refreshTokenService.validateAndConsume(cookieToken);
            Usuario usuario = refreshToken.getUsuario();

            if (!usuario.isEnabled()) {
                refreshTokenService.deleteByUsuario(usuario.getIdUsuario());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .header(HttpHeaders.SET_COOKIE, refreshTokenService.buildClearCookie().toString())
                        .body("Usuário inativo.");
            }

            String newAccessToken = tokenService.generateToken(usuario);
            boolean rememberMe = refreshToken.isRememberMe();
            String newRefreshTokenValue = refreshTokenService.createRefreshToken(usuario, rememberMe);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, refreshTokenService.buildCookie(newRefreshTokenValue, rememberMe).toString())
                    .body(Map.of("token", newAccessToken));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header(HttpHeaders.SET_COOKIE, refreshTokenService.buildClearCookie().toString())
                    .body(e.getMessage());
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String cookieToken = refreshTokenService.extractFromCookie(request);
        if (cookieToken != null) {
            refreshTokenService.logoutByToken(cookieToken);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenService.buildClearCookie().toString())
                .build();
    }
}
