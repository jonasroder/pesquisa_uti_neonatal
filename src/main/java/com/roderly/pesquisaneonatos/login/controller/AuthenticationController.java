package com.roderly.pesquisaneonatos.login.controller;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.roderly.pesquisaneonatos.infra.security.TokenService;
import com.roderly.pesquisaneonatos.login.dto.request.Authentication;
import com.roderly.pesquisaneonatos.login.dto.response.LoginResponse;
import com.roderly.pesquisaneonatos.login.service.LoginLogService;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LoginLogService loginLogService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid Authentication data, HttpServletRequest request) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            // Usuário autenticado com sucesso
            var usuario = (Usuario) auth.getPrincipal();
            var token = tokenService.generateToken(usuario);

            // Registrar login com sucesso
            loginLogService.registrarLog(usuario, request, "SUCCESS", null);

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            // Registrar login com falha
            loginLogService.registrarLog(null, request, "FAILURE", "Usuário ou senha inválidos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        } catch (Exception e) {
            // Registrar falha genérica
            loginLogService.registrarLog(null, request, "FAILURE", "Erro interno no servidor.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar a solicitação.");
        }
    }


    @PostMapping("/renew")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de token inválido.");
        }

        String oldToken = bearerToken.substring(7);

        try {
            String newToken = tokenService.refreshToken(oldToken);
            return ResponseEntity.ok(Map.of("token", newToken));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }
    }


}
