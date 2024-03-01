package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response;

import java.util.List;

public record SuplementoListagemResponse(
        Long id_suplemento,
        String nome,
        String descricao,
        String dosagem,
        String instrucoes_uso,
        Boolean is_active,
        List<SuplementoIngredienteListagem> ingredientes
) {
}
