package com.roderly.microbiomelabufu.pessoa.controller;

import com.roderly.microbiomelabufu.arquivo.mapper.ArquivoMapper;
import com.roderly.microbiomelabufu.arquivo.model.Arquivo;
import com.roderly.microbiomelabufu.arquivo.repository.ArquivoRepository;
import com.roderly.microbiomelabufu.arquivo.service.FotoPerfilService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.infra.FileStorageProperties;
import com.roderly.microbiomelabufu.pessoa.dto.request.PessoaCompletoRequest;
import com.roderly.microbiomelabufu.pessoa.dto.response.PessoaCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.mapper.PessoaMapper;
import com.roderly.microbiomelabufu.pessoa.model.Pessoa;
import com.roderly.microbiomelabufu.pessoa.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ArquivoRepository arquivoRepository;
    @Autowired
    private FileStorageProperties fileStorageLocation;



    @PostMapping("/insert")
    public ResponseEntity<ApiResponseDTO> insert(@RequestBody @Valid PessoaCompletoRequest request) throws IOException {
        Pessoa pessoa = PessoaMapper.PessoaCompletoRequestToPessoa(request);
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);


        Arquivo updatedFile = ArquivoMapper.fotoPerfilRequestToArquivo(request.foto_perfil().metadata(), pessoaSalva.getId_pessoa(),null);
        Arquivo saved = arquivoRepository.save(updatedFile);
        FotoPerfilService.saveBase64ToFile(request.foto_perfil().base64(), fileStorageLocation, updatedFile.getId_arquivo());


        ApiResponseDTO responseDTO = new ApiResponseDTO(pessoaSalva.getId_pessoa(), "Pessoa criada com sucesso");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<PessoaCompletoResponse> getPessoaComEndereco(@PathVariable Long id) {
        Tuple tuple = pessoaRepository.findPessoaWithImageProfile(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));


        Pessoa pessoa = tuple.get(0, Pessoa.class);
        String caminhoArquivo = tuple.get(1, String.class);
        PessoaCompletoResponse response = PessoaMapper.pessoaToPessoaCompletoResponse(pessoa, caminhoArquivo);


        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ApiResponseDTO> update(@RequestBody @Valid PessoaCompletoRequest request) throws IOException {

        Pessoa responsePessoa = pessoaRepository.findById(request.id_pessoa())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + request.id_pessoa()));
        Pessoa updatedPessoa = PessoaMapper.PessoaCompletoRequestToPessoa(request);
        Pessoa pessoa = pessoaRepository.save(updatedPessoa);


        Arquivo savedFile = arquivoRepository.findByEntidadeAndId_entidade("pessoa", updatedPessoa.getId_pessoa()).orElse(null);
        Long id_arquivo = null;
        if (savedFile != null) {
            id_arquivo = savedFile.getId_arquivo();
        }
        Arquivo updatedFile = ArquivoMapper.fotoPerfilRequestToArquivo(request.foto_perfil().metadata(), updatedPessoa.getId_pessoa(), id_arquivo);
        Arquivo saved = arquivoRepository.save(updatedFile);
        FotoPerfilService.saveBase64ToFile(request.foto_perfil().base64(), fileStorageLocation, updatedFile.getId_arquivo());


        ApiResponseDTO response = new ApiResponseDTO(updatedPessoa.getId_pessoa(), "Pessoa atualizada com sucesso.");
        return ResponseEntity.ok(response);
    }
}
