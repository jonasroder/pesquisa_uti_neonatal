package com.roderly.microbiomelabufu.pessoa.dto.request;

import com.roderly.microbiomelabufu.endereco.dto.response.EnderecoResponse;

import java.time.LocalDate;
import java.util.List;

public record PessoaCompletoRequest(
        Long id_pessoa,
        Integer id_tipo_pessoa,
        String nome,
        String sobrenome,
        Integer id_sexo,
        LocalDate data_nascimento,
        String telefone,
        String email,
        Integer id_estado_civil,
        Integer id_profissao,
        String cpf,
        Integer id_etnia,
        Integer id_escolaridade,
        Integer id_religiao
        //List<EnderecoResponse> enderecos
) {}
