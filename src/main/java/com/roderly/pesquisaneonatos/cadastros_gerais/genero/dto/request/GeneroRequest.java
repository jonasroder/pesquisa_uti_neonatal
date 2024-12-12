package com.roderly.pesquisaneonatos.cadastros_gerais.genero.dto.request;

public record GeneroRequest(
        Long idGenero,
        String descricao,
        Long codigo,
        Boolean isActive
) {
}