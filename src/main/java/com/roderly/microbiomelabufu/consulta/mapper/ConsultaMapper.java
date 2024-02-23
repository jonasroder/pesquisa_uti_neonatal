package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.Suplemento;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.model.TipoInformacaoSaude;
import com.roderly.microbiomelabufu.consulta.dto.request.*;
import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaCompletoResponse;
import com.roderly.microbiomelabufu.consulta.model.*;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;
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


}
