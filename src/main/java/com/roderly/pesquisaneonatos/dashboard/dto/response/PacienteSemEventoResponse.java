package com.roderly.pesquisaneonatos.dashboard.dto.response;

public record PacienteSemEventoResponse(
        Long idNeonato,
        String protocolo,
        String dataInternacao,
        String ultimoEvento,
        String usuario
) {
}
