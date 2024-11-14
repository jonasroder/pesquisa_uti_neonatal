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
                String[] methodParts = mapping.getProperty().split("_");

                try {
                    Object value = null;
                    if (methodParts.length == 3) {
                        // Caso onde existe um índice para uma lista
                        String listMethod = methodParts[0];
                        int index = Integer.parseInt(methodParts[1]);
                        String internalMethod = methodParts[2];

                        // Acessa a lista usando o nome do metodo
                        Method getListMethod = dataItem.getClass().getMethod("get" + capitalize(listMethod));
                        List<?> list = (List<?>) getListMethod.invoke(dataItem);

                        // Verifica se o índice está dentro do tamanho da lista
                        if (list != null && list.size() > index) {
                            Object listItem = list.get(index);

                            // Acessa o campo interno do objeto na lista
                            Method getInternalMethod = listItem.getClass().getMethod("get" + capitalize(internalMethod));
                            value = getInternalMethod.invoke(listItem);
                        }
                    } else if (methodParts.length == 2) {
                        // Caso onde o metodo acessa diretamente um campo de uma classe sem índice de lista
                        String mainObjectMethod = methodParts[0];
                        String internalField = methodParts[1];

                        // Acessa o objeto interno na classe principal
                        Method mainMethod = dataItem.getClass().getMethod("get" + capitalize(mainObjectMethod));
                        Object internalObject = mainMethod.invoke(dataItem);

                        if (internalObject != null) {
                            // Acessa o campo dentro do objeto
                            Method internalMethod = internalObject.getClass().getMethod("get" + capitalize(internalField));
                            value = internalMethod.invoke(internalObject);
                        }
                    } else {
                        // Caso simples onde não há índice (propriedade direta)
                        String methodName = mapping.getProperty();
                        Method method = dataItem.getClass().getMethod("get" + capitalize(methodName));
                        value = method.invoke(dataItem);
                    }

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

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
