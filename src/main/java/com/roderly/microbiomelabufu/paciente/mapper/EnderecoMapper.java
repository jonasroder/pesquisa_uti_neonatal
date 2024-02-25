package com.roderly.microbiomelabufu.paciente.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.uf.model.UF;
import com.roderly.microbiomelabufu.paciente.dto.request.EnderecoRequest;
import com.roderly.microbiomelabufu.paciente.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.paciente.model.Endereco;
import com.roderly.microbiomelabufu.paciente.model.Paciente;

public class EnderecoMapper {

    public static EnderecoResponse enderecoToEnderecoResponse(Endereco response){
        return new EnderecoResponse(
                response.getId_endereco(),
                response.getLogradouro(),
                response.getNumero(),
                response.getComplemento(),
                response.getBairro(),
                response.getCidade(),
                response.getUf() != null ? response.getUf().getId_uf() : null,
                response.getCep(),
                response.getReferencia()
        );
    }


    public static Endereco enderecoRequestToEndereco(EnderecoRequest request, Paciente paciente){
        Endereco endereco = new Endereco();
        UF uf = new UF(request.id_uf());

        endereco.setId_endereco(request.id_endereco());
        endereco.setPaciente(paciente);
        endereco.setLogradouro(request.logradouro());
        endereco.setNumero(request.numero());
        endereco.setComplemento(request.complemento());
        endereco.setBairro(request.bairro());
        endereco.setCidade(request.cidade());
        endereco.setCep(request.cep());
        endereco.setReferencia(request.referencia());
        if(request.id_uf() != null){ endereco.setUf(uf); }

        return endereco;
    }




}
