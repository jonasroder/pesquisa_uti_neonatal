package com.roderly.microbiomelabufu.neonato.dto.response;

import java.time.LocalDate;

public record NeonatoListResponse(
        Long idNeonato,
        String nomeMae,
        String prontuario,
        String dataNascimento,
        String dataInternacao,
        String dataDesfecho
) {
}
