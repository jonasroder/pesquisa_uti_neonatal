package com.roderly.microbiomelabufu.pessoa.mapper;

import com.roderly.microbiomelabufu.endereco.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.endereco.mapper.EnderecoMapper;
import com.roderly.microbiomelabufu.pessoa.dto.request.PessoaCompletoRequest;
import com.roderly.microbiomelabufu.pessoa.dto.response.PessoaCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.model.Pessoa;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaMapper {
    public static Pessoa PessoaCompletoRequestToPessoa(PessoaCompletoRequest request){
        Pessoa pessoa = new Pessoa();
        pessoa.setId_pessoa(request.id_pessoa());
        pessoa.setId_tipo_pessoa(request.id_tipo_pessoa());
        pessoa.setNome(request.nome());
        pessoa.setSobrenome(request.sobrenome());
        pessoa.setId_sexo(request.id_sexo());
        pessoa.setData_nascimento(request.data_nascimento());
        pessoa.setTelefone(request.telefone());
        pessoa.setEmail(request.email());
        pessoa.setId_estado_civil(request.id_estado_civil());
        pessoa.setId_profissao(request.id_profissao());
        pessoa.setCpf(request.cpf());
        pessoa.setId_etnia(request.id_etnia());
        pessoa.setId_escolaridade(request.id_escolaridade());
        pessoa.setId_religiao(request.id_religiao());


        if (request.endereco() != null) {
            pessoa.setEndereco(request.endereco().stream()
                    .map(EnderecoMapper::enderecoRequestToEndereco)
                    .collect(Collectors.toList()));
            pessoa.getEndereco().forEach(endereco -> endereco.setPessoa(pessoa));
        }

        return pessoa;
    }

    public static PessoaCompletoResponse pessoaToPessoaCompletoResponse(Pessoa response){

        List<EnderecoResponse> enderecosResponse = null;

        if (response.getEndereco() != null && !response.getEndereco().isEmpty()) {
            enderecosResponse = response.getEndereco().stream()
                    .map(EnderecoMapper::enderecoToEnderecoResponse)
                    .collect(Collectors.toList());
        }

        PessoaCompletoResponse pessoaCompletoResponse = new PessoaCompletoResponse(
                response.getId_pessoa(),
                response.getId_tipo_pessoa(),
                response.getNome(),
                response.getSobrenome(),
                response.getId_sexo(),
                response.getData_nascimento(),
                response.getTelefone(),
                response.getEmail(),
                response.getId_estado_civil(),
                response.getId_profissao(),
                response.getCpf(),
                response.getId_etnia(),
                response.getId_escolaridade(),
                response.getId_religiao(),
                enderecosResponse

        );

        return pessoaCompletoResponse;
    }




}
