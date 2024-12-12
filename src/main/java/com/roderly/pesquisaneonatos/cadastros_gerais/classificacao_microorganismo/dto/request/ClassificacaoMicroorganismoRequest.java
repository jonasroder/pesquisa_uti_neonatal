package com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.dto.request;

public record ClassificacaoMicroorganismoRequest(
        Long idClassificacaoMicroorganismo,
        String descricao,
        Long codigo,
        Boolean isActive
) {
}