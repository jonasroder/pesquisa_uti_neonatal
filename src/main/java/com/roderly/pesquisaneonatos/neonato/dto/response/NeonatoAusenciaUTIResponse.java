package com.roderly.pesquisaneonatos.neonato.dto.response;

import java.time.LocalDate;

public record NeonatoAusenciaUTIResponse(
        Long idNeonatosAusenciaUti,
        LocalDate dataSaidaUti,
        LocalDate dataRetornoUti
) {
}
