package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response;

public record SuplementoResponse(
        Long id_suplemento,
        String nome,
        String descricao,
        String dosagem,
        String instrucoes_uso,
        Boolean is_active
) {
}
