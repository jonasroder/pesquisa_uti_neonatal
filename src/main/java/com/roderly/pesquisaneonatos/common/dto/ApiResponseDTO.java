package com.roderly.pesquisaneonatos.common.dto;

import java.util.List;
import java.util.Map;

public record ApiResponseDTO(
        Long id,                 // ID único, quando necessário
        List<Long> ids,          // Lista de IDs, quando necessário
        String message,          // Mensagem de retorno
        Map<String, Object> additionalData,  // Dados adicionais, quando necessário
        boolean success          // Status de sucesso ou falha
) {

    // Construtor básico para uma mensagem de sucesso com um único ID
    public ApiResponseDTO(Long id, String message) {
        this(id, null, message, null, true);
    }

    // Construtor básico para uma lista de IDs e uma mensagem de sucesso
    public ApiResponseDTO(List<Long> ids, String message) {
        this(null, ids, message, null, true);
    }

    // Construtor completo
    public ApiResponseDTO(Long id, List<Long> ids, String message, Map<String, Object> additionalData, boolean success) {
        this.id = id;
        this.ids = ids;
        this.message = message;
        this.additionalData = additionalData;
        this.success = success;
    }

    // Metodo de fábrica para uma resposta de sucesso com um único ID
    public static ApiResponseDTO success(Long id, String message) {
        return new ApiResponseDTO(id, message);
    }

    // Metodo de fábrica para uma resposta de sucesso com uma lista de IDs
    public static ApiResponseDTO success(List<Long> ids, String message) {
        return new ApiResponseDTO(ids, message);
    }

    // Metodo de fábrica para uma resposta de falha
    public static ApiResponseDTO failure(String message) {
        return new ApiResponseDTO(null, null, message, null, false);
    }

    // Metodo de fábrica para uma resposta de sucesso sem IDs
    public static ApiResponseDTO successMessage(String message) {
        return new ApiResponseDTO((Long) null, message);

    }
}
