package com.roderly.microbiomelabufu.endereco.dto;

public record EnderecoDTO(
        Long id_endereco,
        Long id_pessoa,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String id_uf,
        String cep,
        String referencia
){}
