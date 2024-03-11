package com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.request;

import java.time.LocalTime;

public record FormularioAtividadeFisicaRequest(
        Long id_formulario_atividade_fisica,
        Long id_consulta,
        LocalTime tempo_atividade_vigorosa,
        Integer dias_atividade_vigorosa,
        LocalTime tempo_atividade_moderada,
        Integer dias_atividade_moderada,
        LocalTime tempo_caminhada_moderada,
        Integer dias_caminhada_moderada,
        LocalTime tempo_sentado_dia_util,
        LocalTime tempo_sentado_fds

) {
}
