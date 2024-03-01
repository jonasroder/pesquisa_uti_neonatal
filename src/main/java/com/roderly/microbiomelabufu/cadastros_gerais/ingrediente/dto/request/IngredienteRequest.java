package com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.request;

public record IngredienteRequest(
        Long id_ingrediente,
        String nome,
        String descricao,
        Boolean is_active
) {
}
