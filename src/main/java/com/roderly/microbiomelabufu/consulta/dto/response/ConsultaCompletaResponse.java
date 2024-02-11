package com.roderly.microbiomelabufu.consulta.dto.response;

import java.time.LocalDate;

public record ConsultaCompletaResponse(
        Long id_paciente,
        String foto_perfil,
        String nome,
        Integer idade,
        String plano_saude,
        String telefone_1,
        String telefone_2
) {}
