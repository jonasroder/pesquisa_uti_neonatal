package com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.dto.request;

public record TipoInformacaoSaudeRequest(
        Long id_tipo_informacao_saude,
        String nome,
        String descricao,
        Boolean is_active
) {
}
