package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response;

import java.util.List;

public record SuplementoPageResponse(
        SuplementoResponse suplementoResponse,
        List<SuplementoIngredienteResponse> arrSuplementosIngredientes
) {
}
