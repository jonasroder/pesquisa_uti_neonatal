package com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.dto.response;

public record ClasseAntimicrobianoResponse(
        Long idClasseAntimicrobiano,
        String descricao,
        Long codigo,
        Long idTipoAntimicrobiano,
        Boolean isActive
) {
}