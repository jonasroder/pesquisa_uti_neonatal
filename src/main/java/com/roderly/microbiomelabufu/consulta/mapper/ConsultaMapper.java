package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.consulta.dto.request.ConsultaCompletoRequest;
import com.roderly.microbiomelabufu.consulta.dto.request.PacienteMedicamentoRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaCompletoResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.consulta.model.ConsultaDiagnostico;
import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;
import com.roderly.microbiomelabufu.diagnostico.model.Diagnostico;
import com.roderly.microbiomelabufu.paciente.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ConsultaMapper {
    public static ConsultaCompletoResponse consultaToConsultaCompletoResponse(Paciente responsePaciente, String caminhoFotoPerfil, String planoDeSaude) {

        LocalDate dataNascimento = responsePaciente.getData_nascimento();
        Integer idade = (dataNascimento != null) ? Period.between(dataNascimento, LocalDate.now()).getYears() : null;

        return new ConsultaCompletoResponse(
                responsePaciente.getId_paciente(),
                caminhoFotoPerfil,
                responsePaciente.getNome() + " " + responsePaciente.getSobrenome(),
                idade,
                planoDeSaude,
                responsePaciente.getTelefone_1(),
                responsePaciente.getTelefone_2()
        );
    }


    public static Consulta conusltaCompletoRequestToConsulta(ConsultaCompletoRequest request, Paciente paciente) {
        Consulta consulta = new Consulta();

        consulta.setId_consulta(request.id_consulta());
        consulta.setPaciente(paciente);
        consulta.setId_tipo_consulta(request.id_tipo_consulta());
        consulta.setData_hora(LocalDateTime.now());
        consulta.setObservacoes(request.observacoes());
        consulta.setSintomas(request.sintomas());

        return consulta;
    }


    public static ConsultaDiagnostico conusltaCompletoRequestToConsultaDiagnostico(Consulta consulta, Diagnostico diagnostico) {
        ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico();

        consultaDiagnostico.setConsulta(consulta);
        consultaDiagnostico.setDiagnostico(diagnostico);

        return consultaDiagnostico;
    }


    public static PacienteMedicamento conusltaCompletoRequestToPacienteMedicamento(Consulta consulta, Medicamento medicamento, PacienteMedicamentoRequest pacienteMedicamentoRequest) {
        PacienteMedicamento pacienteMedicamento = new PacienteMedicamento();

        pacienteMedicamento.setId_paciente_medicamento(pacienteMedicamentoRequest.id_paciente_medicamento());
        pacienteMedicamento.setConsulta(consulta);
        pacienteMedicamento.setMedicamento(medicamento);
        pacienteMedicamento.setId_frequencia(pacienteMedicamentoRequest.id_frequencia());
        pacienteMedicamento.setId_periodo(pacienteMedicamentoRequest.id_periodo());
        pacienteMedicamento.setIs_active(pacienteMedicamentoRequest.is_active());

        return pacienteMedicamento;
    }


}
