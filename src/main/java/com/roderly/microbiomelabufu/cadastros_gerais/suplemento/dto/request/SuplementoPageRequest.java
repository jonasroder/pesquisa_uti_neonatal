package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.request;

import java.util.List;

public record SuplementoPageRequest(
        SuplementoRequest suplementoRequest,
        List<SuplementoIngredienteRequest> suplementosIngredienteRequest
) {
}
