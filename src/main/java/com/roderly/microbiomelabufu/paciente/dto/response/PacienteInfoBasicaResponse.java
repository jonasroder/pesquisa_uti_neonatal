package com.roderly.microbiomelabufu.paciente.dto.response;

public record PacienteInfoBasicaResponse(
        Long id_paciente,
        String foto_perfil,
        String nome,
        Integer idade,
        String plano_saude,
        String telefone_1,
        String telefone_2
) {
}
