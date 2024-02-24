package com.roderly.microbiomelabufu.consulta.dto.response;

public record PrescricaoMedicamentoResponse(
        Long id_prescricao_medicamento,
        Long id_consulta,
        Long id_medicamento,
        String dosagem,
        String instrucoes,
        Long id_frequencia,
        Long id_periodo,
        Boolean is_active
) {
}
