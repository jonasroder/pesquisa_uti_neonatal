package com.roderly.microbiomelabufu.paciente.dto.response;

import com.roderly.microbiomelabufu.consulta.dto.response.ConsultasPacienteBasicoResponse;

import java.util.List;

public record PacienteListagemResponse(
        Long id_paciente,
        String nome,
        Integer idade,
        String telefone_1,
        String telefone_2,
        String foto_perfil,
        String plano_saude,
        String cidade,
        List<ConsultasPacienteBasicoResponse> listaConsultas
) {}
