package com.roderly.pesquisaneonatos.neonato.dto.response;

public record NeonatoListResponse(
        Long idNeonato,
        String nomeMae,
        String prontuario,
        String dataNascimento,
        String dataInternacao,
        String dataDesfecho
) {
}
