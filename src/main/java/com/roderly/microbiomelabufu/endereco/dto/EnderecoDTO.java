package com.roderly.microbiomelabufu.endereco.dto;

import com.roderly.microbiomelabufu.endereco.model.EnderecoModel;

public record EnderecoDTO(
        Long id_endereco,
        Long id_pessoa,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String referencia
){}
