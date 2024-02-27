package com.roderly.microbiomelabufu.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.roderly.microbiomelabufu.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usario.getUsuario())
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
        // Primeiro, validar o token existente. Assumindo que `validateToken` retorna o nome de usuário se válido,
        // ou uma string vazia caso contrário. Você pode ajustar conforme a lógica de validação que você tem.
        String usuario = this.validateToken(oldToken);
        if (usuario.isEmpty()) {
            throw new JWTVerificationException("Token inválido ou expirado");
        }

        // Se o token é válido, gerar um novo token
        Usuario user = new Usuario(); // Você precisará obter o objeto Usuario do seu repositório
        user.setUsuario(usuario);
        return this.generateToken(user);
    }


    private Instant getExpireTimeToken(){
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

}
