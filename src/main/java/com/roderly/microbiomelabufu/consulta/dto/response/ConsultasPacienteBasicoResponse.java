package com.roderly.microbiomelabufu.consulta.dto.response;

import java.time.LocalDate;

public record ConsultasPacienteBasicoResponse(
        Long id_consulta,
        String data,
        String tipo_consulta
) {
}
