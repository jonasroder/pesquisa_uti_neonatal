package com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.response;

public record MedicamentoResponse(
        Long idMedicamento,
        String descricao,
        Long codigo,
        Long idClasseAntimicrobiano,
        Boolean isActive
) {
}