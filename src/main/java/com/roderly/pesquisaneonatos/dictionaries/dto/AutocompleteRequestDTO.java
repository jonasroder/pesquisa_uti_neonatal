package com.roderly.pesquisaneonatos.dictionaries.dto;

public record AutocompleteRequestDTO (
    String idColumn,
    String descColumn,
    String tableName,
    Boolean is_active,
    String whereClause
){}
