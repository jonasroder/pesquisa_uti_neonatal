package com.roderly.pesquisaneonatos.prontuario.dto.response;

public record AntibiogramaIsoladoResponse(
        Long idAntibiogramaIsolado,
        Long idIsoladoColeta,
        Long idAntimicrobiano,
        Long idResistenciaMicroorganismo,
        Boolean isActive
) {
}
