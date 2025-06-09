package com.roderly.pesquisaneonatos.dashboard.dto.response;

public record InfeccoesPorAgenteResponse(
        String data,
        String agente,
        Long total,
        String prontuarios
) {}
