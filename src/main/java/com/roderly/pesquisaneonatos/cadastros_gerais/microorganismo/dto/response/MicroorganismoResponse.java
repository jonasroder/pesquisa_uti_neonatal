package com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.dto.response;

public record MicroorganismoResponse(
        Long idMicroorganismo,
        Long idGenero,
        Long idClassificacaoMicroorganismo,
        String especie,
        String acronimo,
        Long codigo,
        Boolean isActive
) {
}