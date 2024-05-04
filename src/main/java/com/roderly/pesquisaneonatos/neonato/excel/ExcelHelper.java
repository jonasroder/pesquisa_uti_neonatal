package com.roderly.pesquisaneonatos.neonato.excel;

import java.util.Map;
import java.util.HashMap;

public class ExcelHelper {
    public static final Map<String, Integer> headerMap = new HashMap<>();

    static {
        String[] headers = {
                "ANO",
                "PRONTUÁRIO",
                "PACIENTE",
                "DATA DE NASCIMENTO",
                "DATA DE INTERNAÇÃO",
                "DATA DO DESFECHO",
                "DIAS DE INTERNAÇÃO",
                "DESFECHO",
                "PROCEDÊNCIA",
                "INDICAÇÃO DE INTERNAÇÃO",
                "SEXO AO NASCIMENTO",
                "PESO AO NASCER",
                "CLASSIFICAÇÃO PESO",
                "CLASSIFICAÇÃO IDADE GESTACIONAL",
                "TIPO DE PARTO",
                "BOLSA ROTA",
                "APGAR 1'",
                "APGAR 5'",
                "MALFORMAÇÃO",
                "SÍTIO DA MALFORMAÇÃO",
                "CIRURGIA",
                "SÍTIO DA CIRURGIA",
        };



        for (int i = 0; i < headers.length; i++) {
            headerMap.put(headers[i], i);
        }
    }

    public static int getColumnIndex(String header) {
        return headerMap.getOrDefault(header, -1);
    }
}
