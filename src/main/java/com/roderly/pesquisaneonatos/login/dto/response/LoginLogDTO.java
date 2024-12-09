package com.roderly.pesquisaneonatos.login.dto.response;

public record LoginLogDTO(
        String dataHora,
        String ipAddress,
        String status,
        String userAgent
){}