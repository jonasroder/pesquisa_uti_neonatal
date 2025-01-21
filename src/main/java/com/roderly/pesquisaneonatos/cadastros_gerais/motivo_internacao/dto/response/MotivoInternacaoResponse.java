package com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.dto.response;

public record MotivoInternacaoResponse(
        Long idMotivoInternacao,
        String descricao,
        Long codigo,
        Boolean isActive
) {
}