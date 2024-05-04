package com.roderly.pesquisaneonatos.neonato.service;

import com.roderly.pesquisaneonatos.common.dto.ApiResponseDTO;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoRequest;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.neonato.excel.ExcelHelper;
import com.roderly.pesquisaneonatos.neonato.mapper.NeonatoMapper;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NeonatoService {

    private final NeonatoRepository neonatoRepository;

    public ApiResponseDTO save (NeonatoRequest request) throws IOException {
        var prontuarioExistente = neonatoRepository.findByProntuario(request.prontuario());
        if (prontuarioExistente.isPresent() && request.idNeonato() == null) {
            return new ApiResponseDTO(null, "Número do prontuário já existente!");
        }

        var neonato = NeonatoMapper.convertNeonatoRequestToNeonato(request);
        var neonatoSalvo = neonatoRepository.save(neonato);

        return new ApiResponseDTO((long) neonatoSalvo.getIdNeonato(), "O registro foi Salvo!");
    }


    public NeonatoResponse load (Long id){
        var neonato = neonatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Neonato não encontrado com ID: " + id));
        return NeonatoMapper.convertNeonatoToNeonatoResponse(neonato);
    }


    public List<NeonatoListResponse> list (){
        var neonatoList = neonatoRepository.findAll();

        return neonatoList.stream()
                .map(NeonatoMapper::convertNeonatoToNeonatoListResponse)
                .toList();

    }



    public byte[] generateExcelReport() throws IOException {

        var neonatos = neonatoRepository.findAll();

        var neonatoList = neonatos.stream()
                .map(NeonatoMapper::convertNeonatoToNeonatoReportData)
                .toList();

        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Neonatos");
            Row headerRow = sheet.createRow(0);

            // Definindo o estilo para o cabeçalho (negrito e background)
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Aplicando o estilo ao cabeçalho
            for (String header : ExcelHelper.headerMap.keySet()) {
                int colIndex = ExcelHelper.getColumnIndex(header);
                Cell cell = headerRow.createCell(colIndex);
                cell.setCellValue(header);
                cell.setCellStyle(headerStyle);
            }

            // Ajustando a largura das colunas automaticamente
            for (int i = 0; i < ExcelHelper.headerMap.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            int rowIdx = 1;
            for (var neonato : neonatoList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(ExcelHelper.getColumnIndex("ANO")).setCellValue(neonato.getAno());
                row.createCell(ExcelHelper.getColumnIndex("PRONTUÁRIO")).setCellValue(neonato.getProntuario());
                row.createCell(ExcelHelper.getColumnIndex("PACIENTE")).setCellValue(neonato.getNomeMae());
                row.createCell(ExcelHelper.getColumnIndex("DATA DE NASCIMENTO")).setCellValue(neonato.getDataNascimento());
                row.createCell(ExcelHelper.getColumnIndex("DATA DE INTERNAÇÃO")).setCellValue(neonato.getDataInternacao());
                row.createCell(ExcelHelper.getColumnIndex("DATA DO DESFECHO")).setCellValue(neonato.getDataDesfecho());
                row.createCell(ExcelHelper.getColumnIndex("DIAS DE INTERNAÇÃO")).setCellValue(neonato.getDiasInternacao());
                row.createCell(ExcelHelper.getColumnIndex("DESFECHO")).setCellValue(neonato.getObito());
                row.createCell(ExcelHelper.getColumnIndex("PROCEDÊNCIA")).setCellValue(neonato.getLocalNascimentoCodigo());
                row.createCell(ExcelHelper.getColumnIndex("INDICAÇÃO DE INTERNAÇÃO")).setCellValue(neonato.getMotivoInternacaoCodigo());
                row.createCell(ExcelHelper.getColumnIndex("SEXO AO NASCIMENTO")).setCellValue(neonato.getSexoCodigo());
                row.createCell(ExcelHelper.getColumnIndex("PESO AO NASCER")).setCellValue(neonato.getPesoGramas());
                row.createCell(ExcelHelper.getColumnIndex("CLASSIFICAÇÃO PESO")).setCellValue(neonato.getPesoNascimentoCodigo());
                row.createCell(ExcelHelper.getColumnIndex("CLASSIFICAÇÃO IDADE GESTACIONAL")).setCellValue(neonato.getIdadeGestacionalCodigo());
                row.createCell(ExcelHelper.getColumnIndex("TIPO DE PARTO")).setCellValue(neonato.getTipoPartoCodigo());
                row.createCell(ExcelHelper.getColumnIndex("BOLSA ROTA")).setCellValue(neonato.getRoturaMembranaCodigo());
                row.createCell(ExcelHelper.getColumnIndex("APGAR 1'")).setCellValue(neonato.getApgar1());
                row.createCell(ExcelHelper.getColumnIndex("APGAR 5'")).setCellValue(neonato.getApgar5());
                row.createCell(ExcelHelper.getColumnIndex("MALFORMAÇÃO")).setCellValue(neonato.getMalformacao());
                row.createCell(ExcelHelper.getColumnIndex("SÍTIO DA MALFORMAÇÃO")).setCellValue(neonato.getSitioMalformacaoCodigo());
                row.createCell(ExcelHelper.getColumnIndex("CIRURGIA")).setCellValue(neonato.getCirurgia());
                row.createCell(ExcelHelper.getColumnIndex("SÍTIO DA CIRURGIA")).setCellValue(neonato.getSitioCirurgiaCodigo());

            }

            workbook.write(out);
            return out.toByteArray();
        }
    }


}
