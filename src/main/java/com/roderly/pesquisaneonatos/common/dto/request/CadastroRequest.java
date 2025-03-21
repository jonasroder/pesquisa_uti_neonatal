package com.roderly.pesquisaneonatos.common.dto.request;

public record CadastroRequest(
        Long id,
        String descricao,
        Long codigo,
        Boolean isActive
) {
}