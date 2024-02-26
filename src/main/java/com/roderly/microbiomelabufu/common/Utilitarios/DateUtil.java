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



}
