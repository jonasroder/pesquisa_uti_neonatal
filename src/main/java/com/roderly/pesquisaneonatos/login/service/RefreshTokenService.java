package com.roderly.pesquisaneonatos.login.service;

import com.roderly.pesquisaneonatos.login.model.RefreshToken;
import com.roderly.pesquisaneonatos.login.repository.RefreshTokenRepository;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private static final String COOKIE_NAME = "refreshToken";
    private static final int SESSION_DAYS = 1;
    private static final int REMEMBER_ME_DAYS = 30;

    @Value("${app.security.cookie.secure:false}")
    private boolean cookieSecure;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public String createRefreshToken(Usuario usuario, boolean rememberMe) {
        refreshTokenRepository.deleteByUsuarioId(usuario.getIdUsuario());

        int days = rememberMe ? REMEMBER_ME_DAYS : SESSION_DAYS;
        String tokenValue = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plus(days, ChronoUnit.DAYS);

        refreshTokenRepository.save(new RefreshToken(tokenValue, usuario, expiresAt, rememberMe));
        return tokenValue;
    }

    @Transactional
    public RefreshToken validateAndConsume(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token inválido"));

        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new IllegalArgumentException("Refresh token expirado");
        }

        refreshTokenRepository.delete(refreshToken);
        return refreshToken;
    }

    @Transactional
    public void deleteByUsuario(Long idUsuario) {
        refreshTokenRepository.deleteByUsuarioId(idUsuario);
    }

    @Transactional
    public void logoutByToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(rt ->
            refreshTokenRepository.deleteByUsuarioId(rt.getUsuario().getIdUsuario())
        );
    }

    public ResponseCookie buildCookie(String tokenValue, boolean rememberMe) {
        int days = rememberMe ? REMEMBER_ME_DAYS : SESSION_DAYS;
        long maxAge = rememberMe ? (long) days * 24 * 60 * 60 : -1;
        return ResponseCookie.from(COOKIE_NAME, tokenValue)
                .httpOnly(true)
                .secure(cookieSecure)
                .path("/authenticate")
                .maxAge(maxAge)
                .sameSite("Strict")
                .build();
    }

    public ResponseCookie buildClearCookie() {
        return ResponseCookie.from(COOKIE_NAME, "")
                .httpOnly(true)
                .secure(cookieSecure)
                .path("/authenticate")
                .maxAge(0)
                .sameSite("Strict")
                .build();
    }

    public String extractFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        for (var cookie : request.getCookies()) {
            if (COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
