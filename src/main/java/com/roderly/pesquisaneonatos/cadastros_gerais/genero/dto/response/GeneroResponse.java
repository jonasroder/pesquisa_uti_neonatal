package com.roderly.pesquisaneonatos.cadastros_gerais.genero.dto.response;

public record GeneroResponse(
        Long idGenero,
        String descricao,
        Long codigo,
        Boolean isActive
) {
}