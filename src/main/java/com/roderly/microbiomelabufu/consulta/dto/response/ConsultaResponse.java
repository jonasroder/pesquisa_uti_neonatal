package com.roderly.microbiomelabufu.consulta.dto.response;

public record ConsultaResponse(
        Long id_consulta,
        Long id_tipo_consulta,
        String sintomas,
        String observacoes,
        Boolean is_active
) {
}
