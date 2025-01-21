package com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.dto.request;

public record MotivoInternacaoRequest(
        Long idMotivoInternacao,
        String descricao,
        Long codigo,
        Boolean isActive
) {
}