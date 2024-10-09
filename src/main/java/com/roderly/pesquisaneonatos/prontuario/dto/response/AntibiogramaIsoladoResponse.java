package com.roderly.pesquisaneonatos.prontuario.dto.response;

public record AntibiogramaIsoladoResponse(
        Long idAntibiogramaIsolado,
        Long idAntimicrobiano,
        Long idResistenciaMicroorganismo
) {
}
