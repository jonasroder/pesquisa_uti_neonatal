package com.roderly.microbiomelabufu.consulta.dto.request;

public record PacienteSuplementoRequest(
        Long id_paciente_suplemento,
        Long id_consulta,
        Long id_suplemento,
        String dosagem,
        Long id_frequencia,
        Long id_periodo,
        Boolean is_active
) {
}
