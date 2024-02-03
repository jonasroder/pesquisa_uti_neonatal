package com.roderly.microbiomelabufu.paciente.controller;

import com.roderly.microbiomelabufu.arquivo.mapper.ArquivoMapper;
import com.roderly.microbiomelabufu.arquivo.model.Arquivo;
import com.roderly.microbiomelabufu.arquivo.repository.ArquivoRepository;
import com.roderly.microbiomelabufu.arquivo.service.FotoPerfilService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.infra.FileStorageProperties;
import com.roderly.microbiomelabufu.paciente.dto.request.PacienteCompletoRequest;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.paciente.mapper.PacienteMapper;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    ArquivoRepository arquivoRepository;
    @Autowired
    private FileStorageProperties fileStorageLocation;



    @PostMapping("/insert")
    public ResponseEntity<ApiResponseDTO> insert(@RequestBody @Valid PacienteCompletoRequest request) throws IOException {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(request.cpf());

        if (pacienteExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDTO(null,"CPF já cadastrado."));
        } else {
            Paciente paciente = PacienteMapper.PacienteCompletoRequestToPaciente(request);
            Paciente pacienteSalva = pacienteRepository.save(paciente);

            Arquivo updatedFile = ArquivoMapper.fotoPerfilRequestToArquivo(request.foto_perfil().metadata(), pacienteSalva.getId_paciente(),null);
            Arquivo saved = arquivoRepository.save(updatedFile);
            FotoPerfilService.saveBase64ToFile(request.foto_perfil().base64(), fileStorageLocation, updatedFile.getId_arquivo());

            return ResponseEntity.ok(new ApiResponseDTO(pacienteSalva.getId_paciente(),"Paciente cadastrado com sucesso."));
        }
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<PacienteCompletoResponse> getPessoaComEndereco(@PathVariable Long id) {
        Tuple tuple = pacienteRepository.findPessoaWithImageProfile(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));


        Paciente paciente = tuple.get(0, Paciente.class);
        String caminhoArquivo = tuple.get(1, String.class);
        PacienteCompletoResponse response = PacienteMapper.pacienteToPacienteCompletoResponse(paciente, caminhoArquivo);


        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ApiResponseDTO> update(@RequestBody @Valid PacienteCompletoRequest request) throws IOException {

        Paciente responsePaciente = pacienteRepository.findById(request.id_paciente())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + request.id_paciente()));
        Paciente updatedPaciente = PacienteMapper.PacienteCompletoRequestToPaciente(request);
        Paciente paciente = pacienteRepository.save(updatedPaciente);


        Arquivo savedFile = arquivoRepository.findByEntidadeAndId_entidade("paciente", updatedPaciente.getId_paciente()).orElse(null);
        Long id_arquivo = null;
        if (savedFile != null) {
            id_arquivo = savedFile.getId_arquivo();
        }
        Arquivo updatedFile = ArquivoMapper.fotoPerfilRequestToArquivo(request.foto_perfil().metadata(), updatedPaciente.getId_paciente(), id_arquivo);
        Arquivo saved = arquivoRepository.save(updatedFile);
        FotoPerfilService.saveBase64ToFile(request.foto_perfil().base64(), fileStorageLocation, updatedFile.getId_arquivo());


        ApiResponseDTO response = new ApiResponseDTO(updatedPaciente.getId_paciente(), "Paciente atualizado com sucesso.");
        return ResponseEntity.ok(response);
    }
}
