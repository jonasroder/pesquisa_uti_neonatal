package com.roderly.pesquisaneonatos.neonato.excel;

import com.roderly.pesquisaneonatos.common.excel.ExcelColumnMapping;

import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static List<ExcelColumnMapping> createColumnMappings() {
        List<ExcelColumnMapping> columnMappings = new ArrayList<>();
        columnMappings.add(new ExcelColumnMapping("ANO", "getAno"));
        columnMappings.add(new ExcelColumnMapping("PRONTUÁRIO", "getProntuario"));
        columnMappings.add(new ExcelColumnMapping("PACIENTE", "getNomeMae"));
        columnMappings.add(new ExcelColumnMapping("DATA DE NASCIMENTO", "getDataNascimento"));
        columnMappings.add(new ExcelColumnMapping("DATA DE INTERNAÇÃO", "getDataInternacao"));
        columnMappings.add(new ExcelColumnMapping("DATA DO DESFECHO", "getDataDesfecho"));
        columnMappings.add(new ExcelColumnMapping("DIAS DE INTERNAÇÃO", "getDiasInternacao"));
        columnMappings.add(new ExcelColumnMapping("DESFECHO", "getObito"));
        columnMappings.add(new ExcelColumnMapping("PROCEDÊNCIA", "getLocalNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("INDICAÇÃO DE INTERNAÇÃO", "getMotivoInternacaoCodigo"));
        columnMappings.add(new ExcelColumnMapping("SEXO AO NASCIMENTO", "getSexoCodigo"));
        columnMappings.add(new ExcelColumnMapping("PESO AO NASCER", "getPesoGramas"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO PESO", "getPesoNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO IDADE GESTACIONAL", "getIdadeGestacionalCodigo"));
        columnMappings.add(new ExcelColumnMapping("TIPO DE PARTO", "getTipoPartoCodigo"));
        columnMappings.add(new ExcelColumnMapping("BOLSA ROTA", "getRoturaMembranaCodigo"));
        columnMappings.add(new ExcelColumnMapping("APGAR 1'", "getApgar1"));
        columnMappings.add(new ExcelColumnMapping("APGAR 5'", "getApgar5"));
        columnMappings.add(new ExcelColumnMapping("MALFORMAÇÃO", "getMalformacao"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA MALFORMAÇÃO", "getSitioMalformacaoCodigo"));
        columnMappings.add(new ExcelColumnMapping("CIRURGIA", "getCirurgia"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA", "getSitioCirurgiaCodigo"));

        return columnMappings;
    }
}
