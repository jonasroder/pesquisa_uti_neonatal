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
        log.setIpAddress(request.getRemoteAddr());
        log.setUserAgent(request.getHeader("User-Agent"));
        log.setStatus(status);
        log.setMotivo(motivo);

        loginLogRepository.save(log);
    }
}