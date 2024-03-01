package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response;

public record SuplementoIngredienteResponse(
        Long id_suplemento_ingrediente,
        String dosagem,
        Boolean is_active,
        Long id_ingrediente
) {
}
