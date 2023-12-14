package com.roderly.microbiomelabufu.pessoa.mapper;

import com.roderly.microbiomelabufu.endereco.mapper.EnderecoMapper;
import com.roderly.microbiomelabufu.endereco.model.Endereco;
import com.roderly.microbiomelabufu.pessoa.dto.request.PessoaCompletoRequest;
import com.roderly.microbiomelabufu.pessoa.dto.response.PessoaCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.model.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaMapper {
    public static Pessoa toPessoa (PessoaCompletoRequest request){
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
        pessoa.setCpf(request.cpf());
        pessoa.setId_etnia(request.id_etnia());
        pessoa.setId_escolaridade(request.id_escolaridade());
        pessoa.setId_religiao(request.id_religiao());

        return pessoa;
    }

    public static PessoaCompletoResponse pessoaToPessoaResponse(Pessoa response){
        return new PessoaCompletoResponse(
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
                response.getId_religiao()
        );
    }


    public static List<PessoaCompletoResponse> toPessoaResponseList(List<Pessoa> pessoas){
        List<PessoaCompletoResponse> responses = new ArrayList<>();
        for (Pessoa pessoa : pessoas){
            responses.add(pessoaToPessoaResponse(pessoa));
        }

        return responses;
    }



}
