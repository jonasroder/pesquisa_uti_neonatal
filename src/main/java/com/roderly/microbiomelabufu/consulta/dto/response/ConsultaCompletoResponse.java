package com.roderly.microbiomelabufu.consulta.dto.response;

public record ConsultaCompletoResponse(
        Long id_paciente,
        String foto_perfil,
        String nome,
        Integer idade,
        String plano_saude,
        String telefone_1,
        String telefone_2
) {}
