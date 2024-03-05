package com.roderly.microbiomelabufu.common.Utilitarios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    /**
     * Converte um LocalDateTime para uma string no formato de data brasileiro (dd/MM/yyyy).
     * @param dateTime O LocalDateTime a ser formatado.
     * @return Uma string representando a data no formato brasileiro.
     */
    public static String LocalDateTimeToDateBR(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }



    /**
     * Extrai a extensão de um nome de arquivo.
     * @param fileName O nome do arquivo do qual extrair a extensão.
     * @return A extensão do arquivo, incluindo o ponto (por exemplo, ".jpg"), ou uma string vazia se o arquivo não tiver extensão.
     */
    public static String extractFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }

}
