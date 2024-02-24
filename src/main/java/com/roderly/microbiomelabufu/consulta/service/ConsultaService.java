package com.roderly.microbiomelabufu.consulta.service;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.repository.DiagnosticoRepository;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.repository.MedicamentoRepository;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.common.persistense.FilterService;
import com.roderly.microbiomelabufu.consulta.dto.request.*;
import com.roderly.microbiomelabufu.consulta.dto.response.*;
import com.roderly.microbiomelabufu.consulta.mapper.ConsultaMapper;
import com.roderly.microbiomelabufu.consulta.model.*;
import com.roderly.microbiomelabufu.consulta.repository.*;
import com.roderly.microbiomelabufu.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ConsultaService {


    @Autowired
    FilterService filterService;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private ConsultaDiagnosticoRepository consultaDiagnosticoRepository;
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private PacienteMedicamentoRepository pacienteMedicamentoRepository;
    @Autowired
    private PacienteSuplementoRepository pacienteSuplementoRepository;
    @Autowired
    private ConsultaInformacaoSaudeRepository consultaInformacaoSaudeRepository;
    @Autowired
    private PrescricaoMedicamentoRepository prescricaoMedicamentoRepository;
    @Autowired
    private PrescricaoSuplementoRepository prescricaoSuplementoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public ApiResponseDTO insertConsulta(ConsultaCompletoRequest request) throws IOException {

        Consulta consulta = ConsultaMapper.conusltaCompletoRequestToConsulta(request);
        Consulta consultaSalva = this.consultaRepository.save(consulta);

        for (ConsultaDiagnosticoRequest consultaDiagnosticoRequest : request.arrConsultaDiagnostico()) {
            ConsultaDiagnostico consultaDiagnostico = ConsultaMapper.consultaDiagnosticoRequestToConsultaDiagnostico(consulta, consultaDiagnosticoRequest);
            this.consultaDiagnosticoRepository.save(consultaDiagnostico);
        }

        for (PacienteMedicamentoRequest pacienteMedicamentoRequest : request.arrMedicamentoUsoPaciente()) {
            PacienteMedicamento pacienteMedicamento = ConsultaMapper.pacienteMedicamentoRequestToPacienteMedicamento(consulta, pacienteMedicamentoRequest);
            this.pacienteMedicamentoRepository.save(pacienteMedicamento);
        }

        for (PacienteSuplementoRequest pacienteSuplementoRequest : request.arrSuplementoUsoPaciente()) {
            PacienteSuplemento pacienteSuplemento = ConsultaMapper.pacienteSuplementoRequestToPacienteSuplemento(consulta, pacienteSuplementoRequest);
            this.pacienteSuplementoRepository.save(pacienteSuplemento);
        }

        for (ConsultaInformacaoSaudeRequest consultaInformacaoSaudeRequest : request.arrInformacaoSaude()) {
            ConsultaInformacaoSaude consultaInformacaoSaude = ConsultaMapper.consultaInformacaoSaudeRequestToConsultaInformacaoSaude(consulta, consultaInformacaoSaudeRequest);
            this.consultaInformacaoSaudeRepository.save(consultaInformacaoSaude);
        }

        for (PrescricaoMedicamentoRequest prescricaoMedicamentoRequest : request.arrPrescricaoMedicamento()) {
            PrescricaoMedicamento prescricaoMedicamento = ConsultaMapper.prescricaoMedicamentoRequestToPrescricaoMedicamento(consulta, prescricaoMedicamentoRequest);
            this.prescricaoMedicamentoRepository.save(prescricaoMedicamento);
        }

        for (PrescricaoSuplementoRequest prescricaoSuplementoRequest : request.arrPrescricaoSuplemento()) {
            PrescricaoSuplemento prescricaoSuplemento = ConsultaMapper.prescricaoSuplementoRequestToPrescricaoSuplemento(consulta, prescricaoSuplementoRequest);
            this.prescricaoSuplementoRepository.save(prescricaoSuplemento);
        }

        return new ApiResponseDTO((long) consultaSalva.getId_consulta(), "A consulta foi salva!");
    }


    public ConsultaCompletaResponse getDadosConsulta(Long id_paciente, Long id_consulta) {

        filterService.applyIsActiveFilter();

        Consulta consulta = new Consulta();
        ConsultaResponse consultaResponse = new ConsultaResponse(0L, 1L, "", "", false);
        Set<PacienteMedicamentoResponse> pacienteMedicamentosResponse = new HashSet<>();
        Set<PacienteSuplementoResponse> pacienteSuplementosResponse = new HashSet<>();
        Set<ConsultaInformacaoSaudeResponse> consultaInformacoesSaudeResponse = new HashSet<>();
        Set<ConsultaDiagnosticoResponse> consultaDiagnosticosResponse = new HashSet<>();
        Set<PrescricaoMedicamentoResponse> prescricaoMedicamentosResponse = new HashSet<>();
        Set<PrescricaoSuplementoResponse> prescricaoSuplementosResponse = new HashSet<>();

        Tuple tuple = this.pacienteRepository.findPessoaWithImageProfile(id_paciente).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrada com ID: " + id_paciente));
        PacienteConsultaResponse pacienteConsultaResponse = ConsultaMapper.pacienteToPacienteConsultaResponse(tuple);

        if (id_consulta != 0) {
            consulta = consultaRepository.findById(id_consulta).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com ID: " + id_consulta));

            consultaResponse = ConsultaMapper.consutaToConsultaResponse(consulta);
            pacienteMedicamentosResponse = ConsultaMapper.pacienteMedicamentoToPacienteMedicamentoResponse(consulta.getPacienteMedicamentos());
            pacienteSuplementosResponse = ConsultaMapper.pacienteSuplementoToPacienteSuplementoResponse(consulta.getPacienteSuplementos());
            consultaInformacoesSaudeResponse = ConsultaMapper.consultaInformacaoSaudeToConsultaInformacaoSaudeResponse(consulta.getConsultaInformacoesSaude());
            consultaDiagnosticosResponse = ConsultaMapper.consultaDiagnosticoToConsutoaDiagnosticoResponse(consulta.getConsultaDiagnosticos());
            prescricaoMedicamentosResponse = ConsultaMapper.prescricaoMedicamentoToPrescricaoMedicamentoResponse(consulta.getPrescricaoMedicamentos());
            prescricaoSuplementosResponse = ConsultaMapper.prescricaoSuplementoToPrescricaoSuplementoResponse(consulta.getPrescricaoSuplementos());
        }

        return new ConsultaCompletaResponse(
                consultaResponse,
                pacienteConsultaResponse,
                pacienteMedicamentosResponse,
                pacienteSuplementosResponse,
                consultaInformacoesSaudeResponse,
                consultaDiagnosticosResponse,
                prescricaoMedicamentosResponse,
                prescricaoSuplementosResponse
        );
    }

}
