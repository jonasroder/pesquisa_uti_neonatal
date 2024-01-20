package com.roderly.microbiomelabufu.pessoa.mapper;

import com.roderly.microbiomelabufu.endereco.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.endereco.mapper.EnderecoMapper;
import com.roderly.microbiomelabufu.pessoa.dto.request.PacienteCompletoRequest;
import com.roderly.microbiomelabufu.pessoa.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.model.Paciente;

import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {
    public static Paciente PacienteCompletoRequestToPaciente(PacienteCompletoRequest request) {
        Paciente paciente = new Paciente();
        paciente.setId_paciente(request.id_paciente());
        paciente.setNome(request.nome());
        paciente.setSobrenome(request.sobrenome());
        paciente.setData_nascimento(request.data_nascimento());
        paciente.setTelefone_1(request.telefone_1());
        paciente.setTelefone_2(request.telefone_2());
        paciente.setEmail(request.email());
        paciente.setCpf(request.cpf());
        paciente.setNaturalidade(request.naturalidade());
        paciente.setNome_pai(request.nome_pai());
        paciente.setNome_mae(request.nome_mae());
        paciente.setNome_conjuge(request.nome_conjuge());
        paciente.setData_nascimento(request.data_ultimo_atendimento());
        paciente.setNum_visitas(request.num_visitas());
        paciente.setIndicacao(request.indicacao());
        paciente.setObservacao(request.observacao());
        paciente.setId_profissao(request.id_profissao());
        paciente.setId_plano_saude(request.id_plano_saude());
        paciente.setId_estado_civil(request.id_estado_civil());
        paciente.setId_sexo(request.id_sexo());
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


    public static PacienteCompletoResponse pacienteToPacienteCompletoResponse(Paciente responsePaciente, String caminhoArquivo) {

        List<EnderecoResponse> enderecosResponse = null;

        if (responsePaciente.getEndereco() != null && !responsePaciente.getEndereco().isEmpty()) {
            enderecosResponse = responsePaciente.getEndereco().stream()
                    .map(EnderecoMapper::enderecoToEnderecoResponse)
                    .collect(Collectors.toList());
        }

        return new PacienteCompletoResponse(
                responsePaciente.getId_paciente(),
                responsePaciente.getNome(),
                responsePaciente.getSobrenome(),
                responsePaciente.getData_nascimento(),
                responsePaciente.getTelefone_1(),
                responsePaciente.getTelefone_2(),
                responsePaciente.getEmail(),
                responsePaciente.getCpf(),
                responsePaciente.getNaturalidade(),
                responsePaciente.getNome_pai(),
                responsePaciente.getNome_mae(),
                responsePaciente.getNome_conjuge(),
                responsePaciente.getData_ultimo_atendimento(),
                responsePaciente.getNum_visitas(),
                responsePaciente.getIndicacao(),
                responsePaciente.getObservacao(),
                responsePaciente.getId_profissao(),
                responsePaciente.getId_plano_saude(),
                responsePaciente.getId_estado_civil(),
                responsePaciente.getId_sexo(),
                responsePaciente.getId_etnia(),
                responsePaciente.getId_escolaridade(),
                responsePaciente.getId_religiao(),
                enderecosResponse,
                caminhoArquivo
        );
    }


}
