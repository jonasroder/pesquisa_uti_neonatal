package com.roderly.pesquisaneonatos.login.service;

import com.roderly.pesquisaneonatos.login.repository.LoginLogRepository;
import com.roderly.pesquisaneonatos.login.model.LoginLog;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginLogService {
    private final LoginLogRepository loginLogRepository;

    public void registrarLog(Usuario usuario, HttpServletRequest request, String status, String motivo) {
        LoginLog log = new LoginLog();
        log.setUsuario(usuario);
        log.setDataHora(LocalDateTime.now());
        log.setIpAddress(getClientIp(request));
        log.setUserAgent(request.getHeader("User-Agent"));
        log.setStatus(status);
        log.setMotivo(motivo);

        loginLogRepository.save(log);
    }

    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr(); // Fallback para o IP direto
        }
        // Se houver múltiplos IPs no cabeçalho (em cenários com proxies encadeados), retorna o primeiro.
        return ipAddress != null ? ipAddress.split(",")[0].trim() : "unknown";
    }
}
