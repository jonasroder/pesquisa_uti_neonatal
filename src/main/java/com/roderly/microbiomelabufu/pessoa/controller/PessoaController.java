package com.roderly.microbiomelabufu.pessoa.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.infra.GlobalExceptionHandler;
import com.roderly.microbiomelabufu.pessoa.dto.LoadPessoaDTO;
import com.roderly.microbiomelabufu.pessoa.dto.SavePessoaDTO;
import com.roderly.microbiomelabufu.pessoa.model.PessoaModel;
import com.roderly.microbiomelabufu.pessoa.repository.PessoaRepository;
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
    public ResponseEntity<ApiResponseDTO> insert(@RequestBody @Valid SavePessoaDTO dados) {

        // Cria uma nova instância de PessoaModel com os dados do DTO
        PessoaModel pessoa = new PessoaModel(dados);

        // Associa cada EnderecoDTO à pessoa usando o método addOrUpdateEndereco
        dados.endereco().forEach(pessoa::addOrUpdateEndereco);

        // Salva a pessoa e os endereços e atualiza a referência
        PessoaModel pessoaSalva = repository.save(pessoa);

        ApiResponseDTO responseDTO = new ApiResponseDTO(
                pessoaSalva.getId_pessoa(),
                "Pessoa criada com sucesso"
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<LoadPessoaDTO> getPessoa(@PathVariable Long id) {
        return repository.findById(id)
                .map(LoadPessoaDTO::fromPessoaModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping ("/update")
    @Transactional
    public ResponseEntity<ApiResponseDTO> update(@RequestBody @Valid SavePessoaDTO dados) {
        PessoaModel pessoa = repository.findById(dados.id_pessoa())
                .orElseThrow(() -> new GlobalExceptionHandler.ResourceNotFoundException("Pessoa não encontrada para este id: " + dados.id_pessoa()));


        pessoa.update(dados); // Chama o método update que agora também atualiza os endereços
        pessoa = repository.save(pessoa); // Salva as alterações no repositório

        ApiResponseDTO responseDTO = new ApiResponseDTO(pessoa.getId_pessoa(), "Pessoa atualizada com sucesso");
        return ResponseEntity.ok(responseDTO);
    }

}
