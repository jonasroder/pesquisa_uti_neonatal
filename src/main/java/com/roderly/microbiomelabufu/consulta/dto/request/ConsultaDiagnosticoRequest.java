package com.roderly.microbiomelabufu.consulta.dto.request;

public record ConsultaDiagnosticoRequest(
        Long id_consulta_diagnostico,
        Long id_consulta,
        Long id_diagnostico,
        Boolean is_active
) {
}
