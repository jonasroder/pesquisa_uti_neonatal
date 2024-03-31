package com.roderly.microbiomelabufu.formulario_hamilton.dto.response;

import com.roderly.microbiomelabufu.paciente.dto.response.PacienteFormularioResponse;

public record FormularioHamiltonPageResponse(
        PacienteFormularioResponse paciente,
        FormularioHamiltonReponse formulario
) {
}
