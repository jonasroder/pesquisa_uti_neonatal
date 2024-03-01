package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.request;

public record SuplementoRequest(
        Long id_suplemento,
        String nome,
        String descricao,
        String dosagem,
        String instrucoes_uso,
        Boolean is_active
) {
}

