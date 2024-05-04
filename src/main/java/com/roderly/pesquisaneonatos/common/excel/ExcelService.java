package com.roderly.pesquisaneonatos.common.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class ExcelService {

    public byte[] generateExcelReport(List<ExcelSheetData<?>> sheetDataList) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            for (ExcelSheetData<?> sheetData : sheetDataList) {
                Sheet sheet = workbook.createSheet(sheetData.getSheetName());
                createHeaderRow(sheet, sheetData.getColumnMappings());
                fillDataRows(sheet, sheetData.getData(), sheetData.getColumnMappings());
            }
            workbook.write(out);
            return out.toByteArray();
        }
    }

    private void createHeaderRow(Sheet sheet, List<ExcelColumnMapping> columnMappings) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = createHeaderStyle(sheet.getWorkbook());
        for (int i = 0; i < columnMappings.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnMappings.get(i).getHeader());
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(i);
        }
    }

    private <T> void fillDataRows(Sheet sheet, List<T> dataList, List<ExcelColumnMapping> columnMappings) {
        int rowIndex = 1;
        for (T dataItem : dataList) {
            Row row = sheet.createRow(rowIndex++);
            for (int i = 0; i < columnMappings.size(); i++) {
                ExcelColumnMapping mapping = columnMappings.get(i);
                String methodName = mapping.getProperty();
                try {
                    Method method = dataItem.getClass().getMethod(methodName);
                    Object value = method.invoke(dataItem);
                    if (value != null) {
                        Cell cell = row.createCell(i);
                        setCellValue(cell, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setCellValue(Cell cell, Object value) {
        if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue(value.toString());
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }
}
