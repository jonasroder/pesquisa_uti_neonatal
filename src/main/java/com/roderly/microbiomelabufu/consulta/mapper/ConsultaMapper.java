package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.Suplemento;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.model.TipoInformacaoSaude;
import com.roderly.microbiomelabufu.consulta.dto.request.*;
import com.roderly.microbiomelabufu.consulta.dto.response.*;
import com.roderly.microbiomelabufu.consulta.model.*;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.usuario.service.UsuarioService;
import jakarta.persistence.Tuple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaMapper {
    public static PacienteConsultaResponse pacienteToPacienteConsultaResponse(Tuple search) {
        Paciente paciente = search.get(0, Paciente.class);
        String caminhoFotoPerfil = search.get(1, String.class);
        String planoDeSaude = search.get(2, String.class);
        LocalDate dataNascimento = paciente.getData_nascimento();
        Integer idade = (dataNascimento != null) ? Period.between(dataNascimento, LocalDate.now()).getYears() : null;

        return new PacienteConsultaResponse(
                paciente.getId_paciente(),
                caminhoFotoPerfil,
                paciente.getNome() + " " + paciente.getSobrenome(),
                idade,
                planoDeSaude,
                paciente.getTelefone_1(),
                paciente.getTelefone_2()
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


    public static ConsultaDiagnostico consultaDiagnosticoRequestToConsultaDiagnostico(Consulta consulta, ConsultaDiagnosticoRequest request) {
        ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico();
        Diagnostico diagnostico = new Diagnostico(request.id_diagnostico());

        consultaDiagnostico.setConsulta(consulta);
        consultaDiagnostico.setDiagnostico(diagnostico);
        consultaDiagnostico.setIs_active(request.is_active());

        return consultaDiagnostico;
    }


    public static PacienteMedicamento pacienteMedicamentoRequestToPacienteMedicamento(Consulta consulta, PacienteMedicamentoRequest request) {
        PacienteMedicamento pacienteMedicamento = new PacienteMedicamento();
        Frequencia frequencia = new Frequencia(request.id_frequencia());
        Periodo periodo = new Periodo(request.id_periodo());
        Medicamento medicamento = new Medicamento(request.id_medicamento());

        pacienteMedicamento.setId_paciente_medicamento(request.id_paciente_medicamento());
        pacienteMedicamento.setConsulta(consulta);
        pacienteMedicamento.setMedicamento(medicamento);
        pacienteMedicamento.setDosagem(request.dosagem());
        pacienteMedicamento.setFrequencia(frequencia);
        pacienteMedicamento.setPeriodo(periodo);
        pacienteMedicamento.setIs_active(request.is_active());

        return pacienteMedicamento;
    }


    public static PacienteSuplemento pacienteSuplementoRequestToPacienteSuplemento(Consulta consulta, PacienteSuplementoRequest request) {
        PacienteSuplemento pacienteSuplemento = new PacienteSuplemento();
        Frequencia frequencia = new Frequencia(request.id_frequencia());
        Periodo periodo = new Periodo(request.id_periodo());
        Suplemento suplemento = new Suplemento(request.id_suplemento());

        pacienteSuplemento.setId_paciente_suplemento(request.id_paciente_suplemento());
        pacienteSuplemento.setConsulta(consulta);
        pacienteSuplemento.setSuplemento(suplemento);
        pacienteSuplemento.setDosagem(request.dosagem());
        pacienteSuplemento.setFrequencia(frequencia);
        pacienteSuplemento.setPeriodo(periodo);
        pacienteSuplemento.setIs_active(request.is_active());

        return pacienteSuplemento;
    }


    public static ConsultaInformacaoSaude consultaInformacaoSaudeRequestToConsultaInformacaoSaude(Consulta consulta, ConsultaInformacaoSaudeRequest request) {
        ConsultaInformacaoSaude consultaInformacaoSaude = new ConsultaInformacaoSaude();
        TipoInformacaoSaude tipoInformacaoSaude = new TipoInformacaoSaude(request.id_tipo_informacao_saude());

        consultaInformacaoSaude.setId_consulta_informacao_saude(request.id_consulta_informacao_saude());
        consultaInformacaoSaude.setConsulta(consulta);
        consultaInformacaoSaude.setValor(request.valor());
        consultaInformacaoSaude.setTipoInformacaoSaude(tipoInformacaoSaude);
        consultaInformacaoSaude.setIs_active(request.is_active());

        return consultaInformacaoSaude;
    }


    public static PrescricaoSuplemento prescricaoSuplementoRequestToPrescricaoSuplemento(Consulta consulta, PrescricaoSuplementoRequest request) {
        PrescricaoSuplemento prescricaoSuplemento = new PrescricaoSuplemento();
        Frequencia frequencia = new Frequencia(request.id_frequencia());
        Periodo periodo = new Periodo(request.id_periodo());
        Suplemento suplemento = new Suplemento(request.id_suplemento());

        prescricaoSuplemento.setId_prescricao_suplemento(request.id_prescricao_suplemento());
        prescricaoSuplemento.setConsulta(consulta);
        prescricaoSuplemento.setSuplemento(suplemento);
        prescricaoSuplemento.setDosagem(request.dosagem());
        prescricaoSuplemento.setInstrucoes(request.instrucoes());
        prescricaoSuplemento.setFrequencia(frequencia);
        prescricaoSuplemento.setPeriodo(periodo);
        prescricaoSuplemento.setIs_active(request.is_active());

        return prescricaoSuplemento;
    }


    public static PrescricaoMedicamento prescricaoMedicamentoRequestToPrescricaoMedicamento(Consulta consulta, PrescricaoMedicamentoRequest request) {
        PrescricaoMedicamento prescricaoMedicamento = new PrescricaoMedicamento();
        Frequencia frequencia = new Frequencia(request.id_frequencia());
        Periodo periodo = new Periodo(request.id_periodo());
        Medicamento medicamento = new Medicamento(request.id_medicamento());

        prescricaoMedicamento.setId_prescricao_medicamento(request.id_prescricao_medicamento());
        prescricaoMedicamento.setConsulta(consulta);
        prescricaoMedicamento.setMedicamento(medicamento);
        prescricaoMedicamento.setDosagem(request.dosagem());
        prescricaoMedicamento.setInstrucoes(request.instrucoes());
        prescricaoMedicamento.setFrequencia(frequencia);
        prescricaoMedicamento.setPeriodo(periodo);
        prescricaoMedicamento.setIs_active(request.is_active());

        return prescricaoMedicamento;
    }


    public static ConsultaResponse consutaToConsultaResponse(Consulta consulta){
        return new ConsultaResponse(
                consulta.getId_consulta(),
                consulta.getId_tipo_consulta(),
                consulta.getSintomas(),
                consulta.getObservacoes(),
                consulta.getIs_active()
        );
    }


    public static Set<PacienteMedicamentoResponse> pacienteMedicamentoToPacienteMedicamentoResponse(Set<PacienteMedicamento> pacienteMedicamentos) {
        return pacienteMedicamentos.stream().map(pacienteMedicamento -> {
            return new PacienteMedicamentoResponse(
                    pacienteMedicamento.getId_paciente_medicamento(),
                    pacienteMedicamento.getConsulta() != null ? pacienteMedicamento.getConsulta().getId_consulta() : null,
                    pacienteMedicamento.getMedicamento() != null ? pacienteMedicamento.getMedicamento().getId_medicamento() : null,
                    pacienteMedicamento.getDosagem(),
                    pacienteMedicamento.getFrequencia() != null ? pacienteMedicamento.getFrequencia().getId_frequencia() : null,
                    pacienteMedicamento.getPeriodo() != null ? pacienteMedicamento.getPeriodo().getId_periodo() : null,
                    pacienteMedicamento.getIs_active()
            );
        }).collect(Collectors.toSet());
    }


    public static Set<PacienteSuplementoResponse> pacienteSuplementoToPacienteSuplementoResponse(Set<PacienteSuplemento> pacienteSuplementos) {
        return pacienteSuplementos.stream().map(pacienteSuplemento -> {
            return new PacienteSuplementoResponse(
                    pacienteSuplemento.getId_paciente_suplemento(),
                    pacienteSuplemento.getConsulta() != null ? pacienteSuplemento.getConsulta().getId_consulta() : null,
                    pacienteSuplemento.getSuplemento() != null ? pacienteSuplemento.getSuplemento().getId_suplemento() : null,
                    pacienteSuplemento.getDosagem(),
                    pacienteSuplemento.getFrequencia() != null ? pacienteSuplemento.getFrequencia().getId_frequencia() : null,
                    pacienteSuplemento.getPeriodo() != null ? pacienteSuplemento.getPeriodo().getId_periodo() : null,
                    pacienteSuplemento.getIs_active()
            );
        }).collect(Collectors.toSet());
    }


    public static Set<ConsultaInformacaoSaudeResponse> consultaInformacaoSaudeToConsultaInformacaoSaudeResponse(Set<ConsultaInformacaoSaude> consultaInformacoesSaude) {
        return consultaInformacoesSaude.stream().map(consultaInformacaoSaude -> {
            return new ConsultaInformacaoSaudeResponse(
                    consultaInformacaoSaude.getId_consulta_informacao_saude(),
                    consultaInformacaoSaude.getConsulta() != null ? consultaInformacaoSaude.getConsulta().getId_consulta() : null,
                    consultaInformacaoSaude.getTipoInformacaoSaude() != null ? consultaInformacaoSaude.getTipoInformacaoSaude().getId_tipo_informacao_saude() : null,
                    consultaInformacaoSaude.getValor(),
                    consultaInformacaoSaude.getIs_active()
            );
        }).collect(Collectors.toSet());
    }


    public static Set<ConsultaDiagnosticoResponse> consultaDiagnosticoToConsutoaDiagnosticoResponse(Set<ConsultaDiagnostico> consultaDiagnosticos) {
        return consultaDiagnosticos.stream().map(consultaDiagnostico -> {
            return new ConsultaDiagnosticoResponse(
                    consultaDiagnostico.getId_consulta_diagnostico(),
                    consultaDiagnostico.getConsulta() != null ? consultaDiagnostico.getConsulta().getId_consulta() : null,
                    consultaDiagnostico.getDiagnostico() != null ? consultaDiagnostico.getDiagnostico().getId_diagnostico() : null,
                    consultaDiagnostico.getIs_active()
            );
        }).collect(Collectors.toSet());
    }


    public static Set<PrescricaoMedicamentoResponse> prescricaoMedicamentoToPrescricaoMedicamentoResponse(Set<PrescricaoMedicamento> prescricaoMedicamentos){
        return prescricaoMedicamentos.stream().map(prescricaoMedicamento -> {
            return new PrescricaoMedicamentoResponse(
                    prescricaoMedicamento.getId_prescricao_medicamento(),
                    prescricaoMedicamento.getConsulta() != null ? prescricaoMedicamento.getConsulta().getId_consulta() : null,
                    prescricaoMedicamento.getMedicamento() != null ? prescricaoMedicamento.getMedicamento().getId_medicamento() : null,
                    prescricaoMedicamento.getDosagem(),
                    prescricaoMedicamento.getInstrucoes(),
                    prescricaoMedicamento.getFrequencia() != null ? prescricaoMedicamento.getFrequencia().getId_frequencia() : null,
                    prescricaoMedicamento.getPeriodo() != null ? prescricaoMedicamento.getPeriodo().getId_periodo() : null,
                    prescricaoMedicamento.getIs_active()
            );
        }).collect(Collectors.toSet());
    }


    public static Set<PrescricaoSuplementoResponse> prescricaoSuplementoToPrescricaoSuplementoResponse(Set<PrescricaoSuplemento> prescricaoSuplementos){
        return prescricaoSuplementos.stream().map(prescricaoSuplementoResponse -> {
            return new PrescricaoSuplementoResponse(
                    prescricaoSuplementoResponse.getId_prescricao_suplemento(),
                    prescricaoSuplementoResponse.getConsulta() != null ? prescricaoSuplementoResponse.getConsulta().getId_consulta() : null,
                    prescricaoSuplementoResponse.getSuplemento() != null ? prescricaoSuplementoResponse.getSuplemento().getId_suplemento() : null,
                    prescricaoSuplementoResponse.getDosagem(),
                    prescricaoSuplementoResponse.getInstrucoes(),
                    prescricaoSuplementoResponse.getFrequencia() != null ? prescricaoSuplementoResponse.getFrequencia().getId_frequencia() : null,
                    prescricaoSuplementoResponse.getPeriodo() != null ? prescricaoSuplementoResponse.getPeriodo().getId_periodo() : null,
                    prescricaoSuplementoResponse.getIs_active()
            );
        }).collect(Collectors.toSet());
    }

}
