package com.roderly.microbiomelabufu.common.dto;


public record ApiResponseDTO(
        Long id,
        String message,
        Object additionalData
) {
    public ApiResponseDTO(Long id, String message) {
        this(id, message, null);
    }
}
