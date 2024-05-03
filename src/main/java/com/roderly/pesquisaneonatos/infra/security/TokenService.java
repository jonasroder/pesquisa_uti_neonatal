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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    private ObjectMapper objectMapper = new ObjectMapper();

    public String generateToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            UserDataResponse userDataResponse = AuthenticationMapper.usuarioToUserDataResponse(usuario);
            String userJsonString;

            try {
                userJsonString = objectMapper.writeValueAsString(userDataResponse);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Erro ao converter dados do usuário para JSON", e);
            }


            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(userJsonString)
                    .withExpiresAt(getExpireTimeToken())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar JWT de autenticação");
        }
    }

    public String validateToken (String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }


    public String refreshToken(String oldToken) {
        String usuario = this.validateToken(oldToken);
        var user = AuthenticationMapper.jsonUserDataFrontToUsuario(usuario);

        if (usuario.isEmpty()) {
            throw new JWTVerificationException("Token inválido ou expirado");
        }

        return this.generateToken(user);
    }


    private Instant getExpireTimeToken(){
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

}
