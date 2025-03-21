package com.roderly.pesquisaneonatos.common.dto.response;

public record CadastroResponse(
        Long id,
        String descricao,
        Long codigo,
        Boolean isActive
) {
}