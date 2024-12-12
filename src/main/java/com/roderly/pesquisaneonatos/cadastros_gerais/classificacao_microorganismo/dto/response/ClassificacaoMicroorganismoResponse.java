package com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.dto.response;

public record ClassificacaoMicroorganismoResponse(
        Long idClassificacaoMicroorganismo,
        String descricao,
        Long codigo,
        Boolean isActive
) {
}