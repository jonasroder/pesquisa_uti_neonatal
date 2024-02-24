package com.roderly.microbiomelabufu.consulta.dto.response;

public record PacienteMedicamentoResponse(
        Long id_paciente_medicamento,
        Long id_consulta,
        Long id_medicamento,
        String dosagem,
        Long id_frequencia,
        Long id_periodo,
        Boolean is_active
) {
}
