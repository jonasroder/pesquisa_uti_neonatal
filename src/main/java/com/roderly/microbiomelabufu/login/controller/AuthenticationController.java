package com.roderly.microbiomelabufu.login.controller;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.roderly.microbiomelabufu.infra.security.TokenService;
import com.roderly.microbiomelabufu.login.dto.AuthenticationDTO;
import com.roderly.microbiomelabufu.login.dto.LoginResponseDTO;
import com.roderly.microbiomelabufu.login.dto.RenewRequestDTO;
import com.roderly.microbiomelabufu.login.service.AuthorizationService;
import com.roderly.microbiomelabufu.usuario.dto.request.UsuarioCompletoRequest;
import com.roderly.microbiomelabufu.usuario.model.Usuario;
import com.roderly.microbiomelabufu.usuario.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        } catch (Exception e) {
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
