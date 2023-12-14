package com.roderly.microbiomelabufu.pessoa.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.pessoa.dto.request.PessoaCompletoRequest;
import com.roderly.microbiomelabufu.pessoa.dto.response.PessoaCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.mapper.PessoaMapper;
import com.roderly.microbiomelabufu.pessoa.model.Pessoa;
import com.roderly.microbiomelabufu.pessoa.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository repository;

    @PostMapping("/insert")
    public ResponseEntity<ApiResponseDTO> insert(@RequestBody @Valid PessoaCompletoRequest request) {
        Pessoa pessoa       = PessoaMapper.toPessoa(request);
        Pessoa pessoaSalva  = repository.save(pessoa);

        ApiResponseDTO responseDTO = new ApiResponseDTO(pessoaSalva.getId_pessoa(), "Pessoa criada com sucesso");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<PessoaCompletoResponse> getPessoaComEndereco(@PathVariable Long id) {
        Pessoa responsePessoa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));

        PessoaCompletoResponse pessoaCompletoResponse = PessoaMapper.pessoaToPessoaResponse(responsePessoa);

        return ResponseEntity.ok(pessoaCompletoResponse);
    }




    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ApiResponseDTO> update(@RequestBody @Valid PessoaCompletoRequest request) {
        Pessoa responsePessoa = repository.findById(request.id_pessoa())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + request.id_pessoa()));

        Pessoa updatedPessoa = PessoaMapper.toPessoa(request);

        repository.save(updatedPessoa);

        ApiResponseDTO response = new ApiResponseDTO(updatedPessoa.getId_pessoa(), "Pessoa atualizada com sucesso.");
        return ResponseEntity.ok(response);
    }
}
