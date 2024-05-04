package com.roderly.pesquisaneonatos.common.Utilitarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * Converte um LocalDateTime para uma string no formato de data brasileiro (dd/MM/yyyy).
     * @param dateTime O LocalDateTime a ser formatado.
     * @return Uma string representando a data no formato brasileiro.
     */
    public static String LocalDateToDateBR(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }



    /**
     * Converte um LocalDate para uma string no formato de data brasileiro (dd/MM/yyyy).
     * @param date O LocalDate a ser formatado.
     * @return Uma string representando a data no formato brasileiro.
     */
    public static String LocalDateToDateBR(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
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



    /**
     * Calcula a diferença em dias entre duas datas.
     * @param dataInicio A data de início.
     * @param dataFim A data de fim.
     * @return A diferença em dias ou null se as datas não forem válidas.
     */
    public static Integer calcularDiferencaDias(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio != null && dataFim != null) {
            return Math.toIntExact(dataFim.toEpochDay() - dataInicio.toEpochDay());
        } else {
            return null;
        }
    }



    /**
     * Calcula a diferença em dias entre duas datas e horas.
     * @param dataInicio A data e hora de início.
     * @param dataFim A data e hora de fim.
     * @return A diferença em dias ou null se as datas não forem válidas.
     */
    public static Integer calcularDiferencaDias(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio != null && dataFim != null) {
            return Math.toIntExact(dataFim.toLocalDate().toEpochDay() - dataInicio.toLocalDate().toEpochDay());
        } else {
            return null;
        }
    }

}
