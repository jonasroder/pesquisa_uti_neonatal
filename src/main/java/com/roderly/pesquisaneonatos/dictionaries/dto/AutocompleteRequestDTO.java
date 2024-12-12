package com.roderly.pesquisaneonatos.dictionaries.dto;

import java.util.List;

public record AutocompleteRequestDTO(
        String idColumn,
        String descColumn,
        String tableName,
        Boolean is_active,
        String whereClause,
        List<String> additionalColumns
) {}

