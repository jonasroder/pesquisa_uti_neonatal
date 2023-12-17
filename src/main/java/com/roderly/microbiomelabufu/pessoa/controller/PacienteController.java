package com.roderly.microbiomelabufu.pessoa.controller;

import com.roderly.microbiomelabufu.arquivo.mapper.ArquivoMapper;
import com.roderly.microbiomelabufu.arquivo.model.Arquivo;
import com.roderly.microbiomelabufu.arquivo.repository.ArquivoRepository;
import com.roderly.microbiomelabufu.arquivo.service.FotoPerfilService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.infra.FileStorageProperties;
import com.roderly.microbiomelabufu.pessoa.dto.request.PacienteCompletoRequest;
import com.roderly.microbiomelabufu.pessoa.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.mapper.PacienteMapper;
import com.roderly.microbiomelabufu.pessoa.model.Paciente;
import com.roderly.microbiomelabufu.pessoa.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        Paciente paciente = PacienteMapper.PacienteCompletoRequestToPessoa(request);
        Paciente pacienteSalva = pacienteRepository.save(paciente);


        Arquivo updatedFile = ArquivoMapper.fotoPerfilRequestToArquivo(request.foto_perfil().metadata(), pacienteSalva.getId_paciente(),null);
        Arquivo saved = arquivoRepository.save(updatedFile);
        FotoPerfilService.saveBase64ToFile(request.foto_perfil().base64(), fileStorageLocation, updatedFile.getId_arquivo());


        ApiResponseDTO responseDTO = new ApiResponseDTO(pacienteSalva.getId_paciente(), "Pessoa criada com sucesso");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<PacienteCompletoResponse> getPessoaComEndereco(@PathVariable Long id) {
        Tuple tuple = pacienteRepository.findPessoaWithImageProfile(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));


        Paciente paciente = tuple.get(0, Paciente.class);
        String caminhoArquivo = tuple.get(1, String.class);
        PacienteCompletoResponse response = PacienteMapper.pessoaToPessoaCompletoResponse(paciente, caminhoArquivo);


        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ApiResponseDTO> update(@RequestBody @Valid PacienteCompletoRequest request) throws IOException {

        Paciente responsePaciente = pacienteRepository.findById(request.id_pessoa())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + request.id_pessoa()));
        Paciente updatedPaciente = PacienteMapper.PacienteCompletoRequestToPessoa(request);
        Paciente paciente = pacienteRepository.save(updatedPaciente);


        Arquivo savedFile = arquivoRepository.findByEntidadeAndId_entidade("paciente", updatedPaciente.getId_paciente()).orElse(null);
        Long id_arquivo = null;
        if (savedFile != null) {
            id_arquivo = savedFile.getId_arquivo();
        }
        Arquivo updatedFile = ArquivoMapper.fotoPerfilRequestToArquivo(request.foto_perfil().metadata(), updatedPaciente.getId_paciente(), id_arquivo);
        Arquivo saved = arquivoRepository.save(updatedFile);
        FotoPerfilService.saveBase64ToFile(request.foto_perfil().base64(), fileStorageLocation, updatedFile.getId_arquivo());


        ApiResponseDTO response = new ApiResponseDTO(updatedPaciente.getId_paciente(), "Pessoa atualizada com sucesso.");
        return ResponseEntity.ok(response);
    }
}
