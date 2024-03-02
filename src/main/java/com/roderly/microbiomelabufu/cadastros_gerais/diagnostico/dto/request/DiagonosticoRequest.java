package com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.request;

public record DiagonosticoRequest(
        Long id_diagnostico,
        String nome,
        String descricao,
        String codigo_cid,
        Boolean is_active
) {
}
