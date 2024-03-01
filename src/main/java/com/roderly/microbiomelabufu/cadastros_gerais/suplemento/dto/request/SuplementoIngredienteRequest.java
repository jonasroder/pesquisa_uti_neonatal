package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.request;

public record SuplementoIngredienteRequest(
        Long id_suplemento_ingrediente,
        String dosagem,
        Boolean is_active,
        Long id_ingrediente
) {
}
