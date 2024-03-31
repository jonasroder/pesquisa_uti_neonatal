package com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.response;

import com.roderly.microbiomelabufu.paciente.dto.response.PacienteFormularioResponse;

public record FormularioAtividadeFisicaPageResponse(
        PacienteFormularioResponse paciente,
        FormularioAtividadeFisicaResponse formulario

) {
}
