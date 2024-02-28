package com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.request;

public record MedicamentoRequest(
        Long  id_medicamento,
        String nome,
        String dosagem,
        Long id_fabricante_medicamento,
        String instrucoes,
        Boolean is_active

) {
}
