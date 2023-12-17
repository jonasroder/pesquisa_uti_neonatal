package com.roderly.microbiomelabufu.pessoa.mapper;

import com.roderly.microbiomelabufu.endereco.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.endereco.mapper.EnderecoMapper;
import com.roderly.microbiomelabufu.pessoa.dto.request.PacienteCompletoRequest;
import com.roderly.microbiomelabufu.pessoa.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.model.Paciente;

import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {
    public static Paciente PacienteCompletoRequestToPessoa(PacienteCompletoRequest request) {
        Paciente paciente = new Paciente();
        paciente.setId_paciente(request.id_pessoa());
        paciente.setId_paciente(request.id_tipo_pessoa());
        paciente.setNome(request.nome());
        paciente.setSobrenome(request.sobrenome());
        paciente.setId_sexo(request.id_sexo());
        paciente.setData_nascimento(request.data_nascimento());
        paciente.setTelefone_1(request.telefone());
        paciente.setEmail(request.email());
        paciente.setId_estado_civil(request.id_estado_civil());
        paciente.setId_profissao(request.id_profissao());
        paciente.setCpf(request.cpf());
        paciente.setId_etnia(request.id_etnia());
        paciente.setId_escolaridade(request.id_escolaridade());
        paciente.setId_religiao(request.id_religiao());


        if (request.endereco() != null) {
            paciente.setEndereco(request.endereco().stream()
                    .map(EnderecoMapper::enderecoRequestToEndereco)
                    .collect(Collectors.toList()));
            paciente.getEndereco().forEach(endereco -> endereco.setPaciente(paciente));
        }

        return paciente;
    }

    public static PacienteCompletoResponse pessoaToPessoaCompletoResponse(Paciente responsePaciente, String caminhoArquivo) {

        List<EnderecoResponse> enderecosResponse = null;

        if (responsePaciente.getEndereco() != null && !responsePaciente.getEndereco().isEmpty()) {
            enderecosResponse = responsePaciente.getEndereco().stream()
                    .map(EnderecoMapper::enderecoToEnderecoResponse)
                    .collect(Collectors.toList());
        }

        PacienteCompletoResponse pacienteCompletoResponse = new PacienteCompletoResponse(
                responsePaciente.getId_paciente(),
                responsePaciente.getId_paciente(),
                responsePaciente.getNome(),
                responsePaciente.getSobrenome(),
                responsePaciente.getId_sexo(),
                responsePaciente.getData_nascimento(),
                responsePaciente.getTelefone_1(),
                responsePaciente.getEmail(),
                responsePaciente.getId_estado_civil(),
                responsePaciente.getId_profissao(),
                responsePaciente.getCpf(),
                responsePaciente.getId_etnia(),
                responsePaciente.getId_escolaridade(),
                responsePaciente.getId_religiao(),
                enderecosResponse,
                caminhoArquivo

        );

        return pacienteCompletoResponse;
    }


}
