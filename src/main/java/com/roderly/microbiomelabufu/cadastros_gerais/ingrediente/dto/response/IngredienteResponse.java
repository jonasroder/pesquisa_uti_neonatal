package com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.response;

public record IngredienteResponse(
        Long id_ingrediente,
        String nome,
        String descricao,
        Boolean is_active
) {
}
