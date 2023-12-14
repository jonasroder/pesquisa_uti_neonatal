package com.roderly.microbiomelabufu.endereco.mapper;

import com.roderly.microbiomelabufu.endereco.dto.request.EnderecoRequest;
import com.roderly.microbiomelabufu.endereco.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.endereco.model.Endereco;

public class EnderecoMapper {

    public static EnderecoResponse enderecoToEnderecoResponse(Endereco response){
        return new EnderecoResponse(
                response.getId_endereco(),
                response.getLogradouro(),
                response.getNumero(),
                response.getComplemento(),
                response.getBairro(),
                response.getCidade(),
                response.getId_uf(),
                response.getCep(),
                response.getReferencia()
        );
    }


    public static Endereco enderecoResponseToEndereco(EnderecoResponse response) {
        Endereco endereco = new Endereco();
        endereco.setId_endereco(response.id_endereco());
        endereco.setLogradouro(response.logradouro());
        endereco.setNumero(response.numero());
        endereco.setComplemento(response.complemento());
        endereco.setBairro(response.bairro());
        endereco.setCidade(response.cidade());
        endereco.setId_uf(response.id_uf());
        endereco.setCep(response.cep());
        endereco.setReferencia(response.referencia());
        return endereco;
    }


    public static Endereco enderecoRequestToEndereco(EnderecoRequest request){
        Endereco endereco = new Endereco();
        endereco.setId_endereco(request.id_endereco());
        endereco.setLogradouro(request.logradouro());
        endereco.setNumero(request.numero());
        endereco.setComplemento(request.complemento());
        endereco.setBairro(request.bairro());
        endereco.setCidade(request.cidade());
        endereco.setId_uf(request.id_uf());
        endereco.setCep(request.cep());
        endereco.setReferencia(request.referencia());
        return endereco;
    }




}
