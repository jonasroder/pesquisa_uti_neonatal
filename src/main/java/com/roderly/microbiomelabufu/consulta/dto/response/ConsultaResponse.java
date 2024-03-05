package com.roderly.microbiomelabufu.consulta.dto.response;

import java.time.LocalDateTime;

public record ConsultaResponse(
        Long id_consulta,
        Long id_tipo_consulta,
        LocalDateTime data_hora,
        String sintomas,
        String observacoes,
        Boolean is_active
) {
}
