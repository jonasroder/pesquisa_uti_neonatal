package com.roderly.pesquisaneonatos.neonato.dto.request;

import java.time.LocalDate;

public record NeonatoAusenciaUTIRequest(
        Long idNeonatosAusenciaUti,
        LocalDate dataSaidaUti,
        LocalDate dataRetornoUti
) {
}
