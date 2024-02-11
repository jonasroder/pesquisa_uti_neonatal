package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaCompletaResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.endereco.dto.response.EnderecoResponse;
import com.roderly.microbiomelabufu.endereco.mapper.EnderecoMapper;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.paciente.model.Paciente;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaMapper {
    public static ConsultaCompletaResponse consultaToConsultaCompletoResponse(Paciente responsePaciente, String caminhoFotoPerfil, String planoDeSaude) {

        LocalDate dataNascimento = responsePaciente.getData_nascimento();
        Integer idade = (dataNascimento != null) ? Period.between(dataNascimento, LocalDate.now()).getYears() : null;

        return new ConsultaCompletaResponse(
                responsePaciente.getId_paciente(),
                caminhoFotoPerfil,
                responsePaciente.getNome() + " " + responsePaciente.getSobrenome(),
                idade,
                planoDeSaude,
                responsePaciente.getTelefone_1(),
                responsePaciente.getTelefone_2()
        );
    }

}
