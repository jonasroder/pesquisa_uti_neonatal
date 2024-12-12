package com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.dto.request;

public record MicroorganismoRequest(
        Long idMicroorganismo,
        Long idGenero,
        Long idClassificacaoMicroorganismo,
        String especie,
        String acronimo,
        Long codigo,
        Boolean isActive
) {
}