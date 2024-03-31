package com.roderly.microbiomelabufu.consulta.service;

import com.roderly.microbiomelabufu.common.Utilitarios.DateUtil;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.common.persistense.FilterService;
import com.roderly.microbiomelabufu.consulta.dto.request.*;
import com.roderly.microbiomelabufu.consulta.dto.response.*;
import com.roderly.microbiomelabufu.consulta.mapper.*;
import com.roderly.microbiomelabufu.consulta.model.*;
import com.roderly.microbiomelabufu.consulta.repository.*;
import com.roderly.microbiomelabufu.infra.FileStorageProperties;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final FilterService filterService;
    private final PacienteRepository pacienteRepository;
    private final ConsultaRepository consultaRepository;
    private final ConsultaDiagnosticoRepository consultaDiagnosticoRepository;
    private final PacienteMedicamentoRepository pacienteMedicamentoRepository;
    private final PacienteSuplementoRepository pacienteSuplementoRepository;
    private final ConsultaInformacaoSaudeRepository consultaInformacaoSaudeRepository;
    private final PrescricaoMedicamentoRepository prescricaoMedicamentoRepository;
    private final PrescricaoSuplementoRepository prescricaoSuplementoRepository;
    private final ArquivoConsultaRepository arquivoConsultaRepository;
    private final FileStorageProperties fileStorageProperties;

    @Transactional
    public ApiResponseDTO saveConsulta(ConsultaCompletoRequest request) throws IOException {

        Consulta consulta = ConsultaMapper.conusltaCompletoRequestToConsulta(request);
        Consulta consultaSalva = this.consultaRepository.save(consulta);

        for (ConsultaDiagnosticoRequest consultaDiagnosticoRequest : request.arrConsultaDiagnostico()) {
            ConsultaDiagnostico consultaDiagnostico = ConsultaDiagnosticoMapper.consultaDiagnosticoRequestToConsultaDiagnostico(consultaSalva, consultaDiagnosticoRequest);
            this.consultaDiagnosticoRepository.save(consultaDiagnostico);
        }

        for (PacienteMedicamentoRequest pacienteMedicamentoRequest : request.arrMedicamentoUsoPaciente()) {
            if (pacienteMedicamentoRequest.id_medicamento() != null) {
                PacienteMedicamento pacienteMedicamento = PacienteMedicamentoMapper.pacienteMedicamentoRequestToPacienteMedicamento(consultaSalva, pacienteMedicamentoRequest);
                this.pacienteMedicamentoRepository.save(pacienteMedicamento);
            }
        }

        for (PacienteSuplementoRequest pacienteSuplementoRequest : request.arrSuplementoUsoPaciente()) {
            if (pacienteSuplementoRequest.id_suplemento() != null) {
                PacienteSuplemento pacienteSuplemento = PacienteSuplementoMapper.pacienteSuplementoRequestToPacienteSuplemento(consultaSalva, pacienteSuplementoRequest);
                this.pacienteSuplementoRepository.save(pacienteSuplemento);
            }
        }

        for (ConsultaInformacaoSaudeRequest consultaInformacaoSaudeRequest : request.arrInformacaoSaude()) {
            if (consultaInformacaoSaudeRequest.id_tipo_informacao_saude() != null) {
                ConsultaInformacaoSaude consultaInformacaoSaude = ConsultaInformacaoSaudeMapper.consultaInformacaoSaudeRequestToConsultaInformacaoSaude(consultaSalva, consultaInformacaoSaudeRequest);
                this.consultaInformacaoSaudeRepository.save(consultaInformacaoSaude);
            }
        }

        for (PrescricaoMedicamentoRequest prescricaoMedicamentoRequest : request.arrPrescricaoMedicamento()) {
            if (prescricaoMedicamentoRequest.id_medicamento() != null) {
                PrescricaoMedicamento prescricaoMedicamento = PrescricaoMedicamentoMapper.prescricaoMedicamentoRequestToPrescricaoMedicamento(consultaSalva, prescricaoMedicamentoRequest);
                this.prescricaoMedicamentoRepository.save(prescricaoMedicamento);
            }
        }

        for (PrescricaoSuplementoRequest prescricaoSuplementoRequest : request.arrPrescricaoSuplemento()) {
            if (prescricaoSuplementoRequest.id_suplemento() != null) {
                PrescricaoSuplemento prescricaoSuplemento = PrescricaoSuplementoMapper.prescricaoSuplementoRequestToPrescricaoSuplemento(consultaSalva, prescricaoSuplementoRequest);
                this.prescricaoSuplementoRepository.save(prescricaoSuplemento);
            }
        }

        for (ArquivoConsultaRequest arquivoConsultaRequest : request.arrConsultaAnexos()) {
            if (arquivoConsultaRequest.id_arquivo() != null) {
                var arquivo = arquivoConsultaRepository.findById(arquivoConsultaRequest.id_arquivo())
                        .orElseThrow(() -> new RuntimeException("Arquivo não encontrado"));
                var arquivoAtualizado = ArquivoConsultaMapper.arquivoConsultaRequestToArquivoConsulta(arquivoConsultaRequest, arquivo);
                arquivoConsultaRepository.save(arquivoAtualizado);
            }
        }

        return new ApiResponseDTO((long) consultaSalva.getId_consulta(), "A consulta foi salva!");
    }


    @Transactional
    public void upload(List<Long> idConsultas, List<Long> idTiposArquivo, List<MultipartFile> files) throws IOException {

        for (int i = 0; i < files.size(); i++) {
            var arquivo = ArquivoConsultaMapper.uploadToArquivoConsulta(idConsultas.get(i), idTiposArquivo.get(i), files.get(i));
            var arquivoSalvo = arquivoConsultaRepository.save(arquivo);
            saveFile(files.get(i), arquivoSalvo.getId_arquivo());
        }

    }


    public void saveFile(MultipartFile file, Long idArquivo) throws IOException {
        Path directoryPath = Paths.get(fileStorageProperties.getFileStorageLocation()).resolve("arquivos_consulta");
        Files.createDirectories(directoryPath);

        String originalFileName = file.getOriginalFilename();
        String fileExtension = DateUtil.extractFileExtension(originalFileName);

        Path filePath = directoryPath.resolve(idArquivo + fileExtension);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }


    public ResponseEntity<Resource> downloadFile(Long idArquivo) throws IOException {
        ArquivoConsulta arquivo = arquivoConsultaRepository.findById(idArquivo)
                .orElseThrow(() -> new RuntimeException("Arquivo não encontrado com id " + idArquivo));

        String fileBasePath = fileStorageProperties.getFileStorageLocation();
        String fileExtension = arquivo.getFormato();
        String fileName = idArquivo + fileExtension;

        Path filePath = Paths.get(fileBasePath, "arquivos_consulta", fileName);
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Não foi possível ler o arquivo " + fileName);
        }

        String contentType = "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome_arquivo())
                .body(resource);
    }


    public ConsultaCompletaResponse getDadosConsulta(Long id_paciente, Long id_consulta) {

        filterService.applyIsActiveFilter();

        Consulta consulta = new Consulta();
        ConsultaResponse consultaResponse = null;
        PacienteConsultaResponse pacienteConsultaResponse = null;
        Set<PacienteMedicamentoResponse> pacienteMedicamentosResponse = new HashSet<>();
        Set<PacienteSuplementoResponse> pacienteSuplementosResponse = new HashSet<>();
        Set<ConsultaInformacaoSaudeResponse> consultaInformacoesSaudeResponse = new HashSet<>();
        Set<ConsultaDiagnosticoResponse> consultaDiagnosticosResponse = new HashSet<>();
        Set<PrescricaoMedicamentoResponse> prescricaoMedicamentosResponse = new HashSet<>();
        Set<PrescricaoSuplementoResponse> prescricaoSuplementosResponse = new HashSet<>();
        Set<ArquivoConsultaReponse> arquivoConsultaReponse = new HashSet<>();


        var pacienteOpt = pacienteRepository.findById(id_paciente);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            pacienteConsultaResponse = ConsultaMapper.pacienteToPacienteConsultaResponse(paciente);
        }

        if (id_consulta != 0) {
            consulta = consultaRepository.findById(id_consulta).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com ID: " + id_consulta));

            consultaResponse = ConsultaMapper.consutaToConsultaResponse(consulta);
            pacienteMedicamentosResponse = PacienteMedicamentoMapper.pacienteMedicamentoToPacienteMedicamentoResponse(consulta.getPacienteMedicamentos());
            pacienteSuplementosResponse = PacienteSuplementoMapper.pacienteSuplementoToPacienteSuplementoResponse(consulta.getPacienteSuplementos());
            consultaInformacoesSaudeResponse = ConsultaInformacaoSaudeMapper.consultaInformacaoSaudeToConsultaInformacaoSaudeResponse(consulta.getConsultaInformacoesSaude());
            consultaDiagnosticosResponse = ConsultaDiagnosticoMapper.consultaDiagnosticoToConsutoaDiagnosticoResponse(consulta.getConsultaDiagnosticos());
            prescricaoMedicamentosResponse = PrescricaoMedicamentoMapper.prescricaoMedicamentoToPrescricaoMedicamentoResponse(consulta.getPrescricaoMedicamentos());
            prescricaoSuplementosResponse = PrescricaoSuplementoMapper.prescricaoSuplementoToPrescricaoSuplementoResponse(consulta.getPrescricaoSuplementos());
            arquivoConsultaReponse = ArquivoConsultaMapper.arquivoConsultaToArquivoConsultaResponse(consulta.getArquivosConsultas());
        }

        return new ConsultaCompletaResponse(
                consultaResponse,
                pacienteConsultaResponse,
                pacienteMedicamentosResponse,
                pacienteSuplementosResponse,
                consultaInformacoesSaudeResponse,
                consultaDiagnosticosResponse,
                prescricaoMedicamentosResponse,
                prescricaoSuplementosResponse,
                arquivoConsultaReponse
        );
    }

}
