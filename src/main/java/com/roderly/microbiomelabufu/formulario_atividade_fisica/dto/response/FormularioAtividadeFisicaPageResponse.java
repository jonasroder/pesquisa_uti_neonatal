package com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.response;

import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.PacienteFormularioResponse;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.response.FormularioPittsburghResponse;

public record FormularioAtividadeFisicaPageResponse(
        PacienteFormularioResponse paciente,
        FormularioAtividadeFisicaResponse formulario

) {
}
