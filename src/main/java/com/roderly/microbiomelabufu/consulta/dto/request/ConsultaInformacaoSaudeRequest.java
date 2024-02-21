package com.roderly.microbiomelabufu.consulta.dto.request;

public record ConsultaInformacaoSaudeRequest(
        Long id_consulta_informacao_saude,
        Long id_consulta,
        Long id_tipo_informacao_saude,
        String valor,
        Boolean is_active
) {
}
