package com.roderly.microbiomelabufu.endereco.dto.request;

public record EnderecoRequest(
        Long id_endereco,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        Integer id_uf,
        String cep,
        String referencia
){}
