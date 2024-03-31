package com.roderly.microbiomelabufu.formulario_pittsburgh.dto.response;

import com.roderly.microbiomelabufu.paciente.dto.response.PacienteFormularioResponse;

public record FormularioPittsburghPageResponse(
        PacienteFormularioResponse paciente,
        FormularioPittsburghResponse formulario

) {
}
