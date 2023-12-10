package com.roderly.microbiomelabufu.endereco.dto;

import com.roderly.microbiomelabufu.endereco.model.EnderecoModel;

public record LoadEnderecoDTO(
        Long id_endereco,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String referencia
) {
    public LoadEnderecoDTO(EnderecoModel enderecoModel) {
        this(
                enderecoModel.getId_endereco(),
                enderecoModel.getLogradouro(),
                enderecoModel.getNumero(),
                enderecoModel.getComplemento(),
                enderecoModel.getBairro(),
                enderecoModel.getCidade(),
                enderecoModel.getEstado(),
                enderecoModel.getCep(),
                enderecoModel.getReferencia()
        );
    }
}
