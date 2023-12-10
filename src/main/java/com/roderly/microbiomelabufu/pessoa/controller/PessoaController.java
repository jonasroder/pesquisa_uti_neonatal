package com.roderly.microbiomelabufu.pessoa.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.endereco.model.EnderecoModel;
import com.roderly.microbiomelabufu.pessoa.dto.LoadPessoaDTO;
import com.roderly.microbiomelabufu.pessoa.dto.SavePessoaDTO;
import com.roderly.microbiomelabufu.pessoa.model.PessoaModel;
import com.roderly.microbiomelabufu.pessoa.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository repository;
    @PostMapping("/insert")
    public ResponseEntity<ApiResponseDTO> insert(@RequestBody @Valid SavePessoaDTO dados) {

        PessoaModel pessoa = new PessoaModel(dados);
        EnderecoModel endereco = new EnderecoModel(dados.endereco());

        // Associa o endereço à pessoa
        pessoa.getEnderecos().add(endereco);

        // Associa a pessoa ao endereço para manter a relação bidirecional
        endereco.setPessoa(pessoa);

        // Salva a pessoa (e o endereço, por cascata)
        pessoa = repository.save(pessoa);


        ApiResponseDTO responseDTO = new ApiResponseDTO(
                pessoa.getId_pessoa(),
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

}
