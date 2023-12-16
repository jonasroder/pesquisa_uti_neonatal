package com.roderly.microbiomelabufu.pessoa.mapper;

import com.roderly.microbiomelabufu.arquivo.model.Arquivo;
import com.roderly.microbiomelabufu.endereco.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.endereco.mapper.EnderecoMapper;
import com.roderly.microbiomelabufu.pessoa.dto.request.PessoaCompletoRequest;
import com.roderly.microbiomelabufu.pessoa.dto.response.PessoaCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.model.Pessoa;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaMapper {
    public static Pessoa PessoaCompletoRequestToPessoa(PessoaCompletoRequest request) {
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

    public static PessoaCompletoResponse pessoaToPessoaCompletoResponse(Pessoa responsePessoa, String caminhoArquivo) {

        List<EnderecoResponse> enderecosResponse = null;

        if (responsePessoa.getEndereco() != null && !responsePessoa.getEndereco().isEmpty()) {
            enderecosResponse = responsePessoa.getEndereco().stream()
                    .map(EnderecoMapper::enderecoToEnderecoResponse)
                    .collect(Collectors.toList());
        }

        PessoaCompletoResponse pessoaCompletoResponse = new PessoaCompletoResponse(
                responsePessoa.getId_pessoa(),
                responsePessoa.getId_tipo_pessoa(),
                responsePessoa.getNome(),
                responsePessoa.getSobrenome(),
                responsePessoa.getId_sexo(),
                responsePessoa.getData_nascimento(),
                responsePessoa.getTelefone(),
                responsePessoa.getEmail(),
                responsePessoa.getId_estado_civil(),
                responsePessoa.getId_profissao(),
                responsePessoa.getCpf(),
                responsePessoa.getId_etnia(),
                responsePessoa.getId_escolaridade(),
                responsePessoa.getId_religiao(),
                enderecosResponse,
                caminhoArquivo

        );

        return pessoaCompletoResponse;
    }


}
