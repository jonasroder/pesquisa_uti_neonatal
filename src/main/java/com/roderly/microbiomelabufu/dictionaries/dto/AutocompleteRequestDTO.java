package com.roderly.microbiomelabufu.dictionaries.dto;

public record AutocompleteRequestDTO (
    String idColumn,
    String descColumn,
    String tableName,
    Boolean situacao,
    String whereClause
){}
