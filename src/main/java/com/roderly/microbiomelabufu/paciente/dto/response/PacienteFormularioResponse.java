package com.roderly.microbiomelabufu.paciente.dto.response;

public record PacienteFormularioResponse(
        Long id_paciente,
        String foto_perfil,
        String nome,
        Integer idade,
        String plano_saude,
        String telefone_1,
        String telefone_2,
        String data,
        String tipo_consulta
) {
}
