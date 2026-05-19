package com.roderly.pesquisaneonatos.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roderly.pesquisaneonatos.login.dto.response.UserDataResponse;
import com.roderly.pesquisaneonatos.login.mapper.AuthenticationMapper;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            UserDataResponse userDataResponse = AuthenticationMapper.usuarioToUserDataResponse(usuario);
            String userJsonString;

            try {
                userJsonString = objectMapper.writeValueAsString(userDataResponse);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Erro ao converter dados do usuário para JSON", e);
            }

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(userJsonString)
                    .withExpiresAt(getExpireTimeToken())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar JWT de autenticação");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant getExpireTimeToken() {
        return Instant.now().plus(30, ChronoUnit.MINUTES);
    }
}
