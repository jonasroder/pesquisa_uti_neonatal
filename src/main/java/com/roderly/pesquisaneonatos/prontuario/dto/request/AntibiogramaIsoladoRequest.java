package com.roderly.pesquisaneonatos.prontuario.dto.request;

public record AntibiogramaIsoladoRequest(
        Long idAntibiogramaIsolado,
        Long idIsoladoColeta,
        Long idAntimicrobiano,
        Long idResistenciaMicroorganismo,
        Boolean isActive
) {
}
