package com.roderly.microbiomelabufu.pessoa.dto.response;

import com.roderly.microbiomelabufu.endereco.dto.response.EnderecoResponse;

import java.time.LocalDate;
import java.util.List;

public record PacienteCompletoResponse(
        Long id_pessoa,
        Long id_tipo_pessoa,
        String nome,
        String sobrenome,
        Long id_sexo,
        LocalDate data_nascimento,
        String telefone,
        String email,
        Long id_estado_civil,
        Long id_profissao,
        String cpf,
        Long id_etnia,
        Long id_escolaridade,
        Long id_religiao,
        List<EnderecoResponse> endereco,
        String foto_perfil
) {}
