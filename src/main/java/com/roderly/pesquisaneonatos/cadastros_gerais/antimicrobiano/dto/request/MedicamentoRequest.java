package com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.request;

public record MedicamentoRequest(
        Long idMedicamento,
        String descricao,
        Long codigo,
        Long idClasseAntimicrobiano,
        Boolean isActive
) {
}