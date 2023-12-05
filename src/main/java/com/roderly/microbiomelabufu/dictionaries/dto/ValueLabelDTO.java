package com.roderly.microbiomelabufu.dictionaries.dto;

import lombok.Getter;

@Getter
public class ValueLabelDTO {
    // Getters e Setters
    private Object value;
    private String label;

    public ValueLabelDTO(Object value, String label) {
        this.value = value;
        this.label = label;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
