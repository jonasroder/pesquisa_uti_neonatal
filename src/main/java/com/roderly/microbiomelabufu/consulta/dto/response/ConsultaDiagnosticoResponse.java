package com.roderly.microbiomelabufu.consulta.dto.response;

public record ConsultaDiagnosticoResponse(
        Long id_consulta_diagnostico,
        Long id_consulta,
        Long id_diagnostico,
        Boolean is_active
) {
}
