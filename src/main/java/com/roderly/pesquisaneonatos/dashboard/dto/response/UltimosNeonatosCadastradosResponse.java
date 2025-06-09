package com.roderly.pesquisaneonatos.dashboard.dto.response;

public record UltimosNeonatosCadastradosResponse(
        Long idNeonato,
        String protocolo,
        String dataNascimento,
        String dataCadastro,
        String usuario
) {
}
