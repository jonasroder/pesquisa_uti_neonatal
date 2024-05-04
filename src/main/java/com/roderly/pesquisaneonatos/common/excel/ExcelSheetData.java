package com.roderly.pesquisaneonatos.common.excel;

import java.util.List;

public class ExcelSheetData<T> {
    private String sheetName;
    private List<T> data;
    private List<ExcelColumnMapping> columnMappings;

    public ExcelSheetData(String sheetName, List<T> data, List<ExcelColumnMapping> columnMappings) {
        this.sheetName = sheetName;
        this.data = data;
        this.columnMappings = columnMappings;
    }

    public String getSheetName() {
        return sheetName;
    }

    public List<T> getData() {
        return data;
    }

    public List<ExcelColumnMapping> getColumnMappings() {
        return columnMappings;
    }
}
