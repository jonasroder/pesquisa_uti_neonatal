package com.roderly.microbiomelabufu.consulta.dto.response;

public record PrescricaoSuplementoResponse(
        Long id_prescricao_suplemento,
        Long id_consulta,
        Long id_suplemento,
        String dosagem,
        String instrucoes,
        Long id_frequencia,
        Long id_periodo,
        Boolean is_active
) {
}
