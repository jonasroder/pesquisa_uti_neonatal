package com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.dto.request;

public record ClasseAntimicrobianoRequest(
        Long idClasseAntimicrobiano,
        String descricao,
        Long codigo,
        Long idTipoAntimicrobiano,
        Boolean isActive
) {
}