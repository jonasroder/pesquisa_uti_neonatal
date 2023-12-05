package com.roderly.microbiomelabufu.dictionaries.dto;

import lombok.Getter;

@Getter
public class AutocompleteRequestDTO {
    private String idColumn;
    private String descColumn;
    private String tableName;
    private Boolean situacao;
    private String whereClause;

}
