package com.roderly.pesquisaneonatos.dictionaries.dto;

import java.util.Map;

public record ValueLabelDTO(
        Long value,
        String label,
        Map<String, Object> additionalData
) {
}
