package com.roderly.microbiomelabufu.agenda.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendaResponse(
        Long id_evento,
        LocalDate data_evento,
        LocalTime hora_inicio,
        LocalTime hora_fim,
        Boolean dia_inteiro,
        String url,
        String descricao,
        Boolean is_active,
        Long id_usuario,
        Long id_status_agenda,
        String status_agenda_cor_associada,
        Long id_tipo_evento,
        String tipo_evento,
        String tipo_evento_cor_associada,
        Long id_paciente
) {
}
