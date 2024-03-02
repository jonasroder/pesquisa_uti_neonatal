package com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.response;

public record DiagnosticoResponse(
        Long id_diagnostico,
        String nome,
        String descricao,
        String codigo_cid,
        Boolean is_active
) {
}
