package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.Suplemento;
import com.roderly.microbiomelabufu.consulta.dto.request.ConsultaCompletoRequest;
import com.roderly.microbiomelabufu.consulta.dto.request.ConsultaDiagnosticoRequest;
import com.roderly.microbiomelabufu.consulta.dto.request.PacienteMedicamentoRequest;
import com.roderly.microbiomelabufu.consulta.dto.request.PacienteSuplementoRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaCompletoResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.consulta.model.ConsultaDiagnostico;
import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;
import com.roderly.microbiomelabufu.consulta.model.PacienteSuplemento;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.usuario.service.UsuarioService;

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


    public static Consulta conusltaCompletoRequestToConsulta(ConsultaCompletoRequest request) {
        Consulta consulta = new Consulta();
        Paciente paciente = new Paciente(request.id_paciente());

        consulta.setId_consulta(request.id_consulta());
        consulta.setPaciente(paciente);
        consulta.setId_tipo_consulta(request.id_tipo_consulta());
        consulta.setData_hora(LocalDateTime.now());
        consulta.setObservacoes(request.observacoes());
        consulta.setSintomas(request.sintomas());
        consulta.setMedico(UsuarioService.getUsuarioAutenticado());

        return consulta;
    }


    public static ConsultaDiagnostico conusltaCompletoRequestToConsultaDiagnostico(Consulta consulta, ConsultaDiagnosticoRequest consultaDiagnosticoRequest) {
        ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico();
        Diagnostico diagnostico = new Diagnostico(consultaDiagnosticoRequest.id_diagnostico());

        consultaDiagnostico.setConsulta(consulta);
        consultaDiagnostico.setDiagnostico(diagnostico);
        consultaDiagnostico.setIs_active(consultaDiagnosticoRequest.is_active());

        return consultaDiagnostico;
    }


    public static PacienteMedicamento conusltaCompletoRequestToPacienteMedicamento(Consulta consulta, PacienteMedicamentoRequest pacienteMedicamentoRequest) {
        PacienteMedicamento pacienteMedicamento = new PacienteMedicamento();
        Frequencia frequencia = new Frequencia(pacienteMedicamentoRequest.id_frequencia());
        Periodo periodo = new Periodo(pacienteMedicamentoRequest.id_periodo());
        Medicamento medicamento = new Medicamento(pacienteMedicamentoRequest.id_medicamento());

        pacienteMedicamento.setId_paciente_medicamento(pacienteMedicamentoRequest.id_paciente_medicamento());
        pacienteMedicamento.setConsulta(consulta);
        pacienteMedicamento.setMedicamento(medicamento);
        pacienteMedicamento.setDosagem(pacienteMedicamentoRequest.dosagem());
        pacienteMedicamento.setFrequencia(frequencia);
        pacienteMedicamento.setPeriodo(periodo);
        pacienteMedicamento.setIs_active(pacienteMedicamentoRequest.is_active());

        return pacienteMedicamento;
    }




    public static PacienteSuplemento conusltaCompletoRequestToPacienteSuplemento(Consulta consulta, PacienteSuplementoRequest pacienteSuplementoRequest) {
        PacienteSuplemento pacienteSuplemento = new PacienteSuplemento();
        Frequencia frequencia = new Frequencia(pacienteSuplementoRequest.id_frequencia());
        Periodo periodo = new Periodo(pacienteSuplementoRequest.id_periodo());
        Suplemento suplemento = new Suplemento(pacienteSuplementoRequest.id_suplemento());

        pacienteSuplemento.setId_paciente_suplemento(pacienteSuplementoRequest.id_paciente_suplemento());
        pacienteSuplemento.setConsulta(consulta);
        pacienteSuplemento.setSuplemento(suplemento);
        pacienteSuplemento.setDosagem(pacienteSuplementoRequest.dosagem());
        pacienteSuplemento.setFrequencia(frequencia);
        pacienteSuplemento.setPeriodo(periodo);
        pacienteSuplemento.setIs_active(pacienteSuplementoRequest.is_active());

        return pacienteSuplemento;
    }


}
