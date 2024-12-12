package com.roderly.pesquisaneonatos.common.Utilitarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class DateUtil {

    /**
     * Converte um LocalDateTime para uma string no formato de data brasileiro (dd/MM/yyyy).
     *
     * @param dateTime O LocalDateTime a ser formatado.
     * @return Uma string representando a data no formato brasileiro ou null se o parâmetro for null.
     */
    public static String LocalDateToDateBR(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }



    /**
     * Converte um LocalDate para uma string no formato de data brasileiro (dd/MM/yyyy).
     *
     * @param date O LocalDate a ser formatado.
     * @return Uma string representando a data no formato brasileiro ou null se o parâmetro for null.
     */
    public static String LocalDateToDateBR(LocalDate date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }



    /**
     * Converte um LocalDateTime para uma string no formato de data e horário brasileiro (dd/MM/yyyy HH:mm:ss).
     *
     * @param dateTime O LocalDateTime a ser formatado.
     * @return Uma string representando a data e o horário no formato brasileiro ou null se o parâmetro for null.
     */
    public static String LocalDateTimeToDateTimeBR(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }


    /**
     * Extrai a extensão de um nome de arquivo.
     *
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
     *
     * @param dataInicio A data de início.
     * @param dataFim    A data de fim.
     * @return A diferença em dias ou null se as datas não forem válidas.
     */
    public static Optional<Long> calcularDiferencaDias(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio != null && dataFim != null) {
            return Optional.of(ChronoUnit.DAYS.between(dataInicio, dataFim));
        } else {
            return Optional.empty();
        }
    }


    /**
     * Calcula a diferença em dias entre duas datas e horas.
     *
     * @param dataInicio A data e hora de início.
     * @param dataFim    A data e hora de fim.
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
