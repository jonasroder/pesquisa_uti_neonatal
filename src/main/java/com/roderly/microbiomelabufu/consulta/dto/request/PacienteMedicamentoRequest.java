package com.roderly.microbiomelabufu.consulta.dto.request;

public record PacienteMedicamentoRequest(
        Long id_paciente_medicamento,
        Long id_consulta,
        Long id_medicamento,
        String dosagem,
        Long id_frequencia,
        Long id_periodo,
        Boolean is_active
) {
}
