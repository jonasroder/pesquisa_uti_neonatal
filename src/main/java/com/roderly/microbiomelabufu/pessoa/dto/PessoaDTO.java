package com.roderly.microbiomelabufu.pessoa.dto;

import com.roderly.microbiomelabufu.endereco.dto.request.EnderecoRequest;

import java.time.LocalDate;
import java.util.List;

public record PessoaDTO(
        Long id_pessoa,
        String fotoPerfil,
        String nome,
        String sobrenome,
        LocalDate data_nascimento,
        String telefone,
        String email,
        Integer id_estado_civil,
        Integer id_profissao,
        String cpf,
        Integer id_etnia,
        Integer id_escolaridade,
        Integer id_religiao,
        Integer id_sexo,
        Integer id_tipo_pessoa,
        List<EnderecoRequest> endereco
) {}
