package com.roderly.microbiomelabufu.agenda.dto.request;

import java.time.LocalDateTime;

public record AgendaRequest(
        Long id_evento,
        LocalDateTime inicio,
        LocalDateTime fim,
        Boolean dia_inteiro,
        String url,
        String descricao,
        Boolean is_active,
        Long id_usuario,
        Long id_status_agenda,
        Long id_tipo_evento_agenda,
        Long id_paciente
) {
}
