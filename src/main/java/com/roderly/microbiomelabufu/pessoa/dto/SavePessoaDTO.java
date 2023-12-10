package com.roderly.microbiomelabufu.pessoa.dto;

import com.roderly.microbiomelabufu.endereco.dto.EnderecoDTO;

import java.time.LocalDate;

public record SavePessoaDTO(
        Long id_pessoa,
        String fotoPerfil,
        String nome,
        String sobrenome,
        String sexo,
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
        EnderecoDTO endereco
) {}
