package com.roderly.microbiomelabufu.formulario_alimentacao.dto.response;

import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.PacienteFormularioResponse;

public record FormularioAlimentarPageResponse(
        PacienteFormularioResponse paciente,
        FormularioAlimentarResponse formulario

) {
}
