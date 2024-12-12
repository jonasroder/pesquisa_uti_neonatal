package com.roderly.pesquisaneonatos.cadastros_gerais.genero.controller;


import com.roderly.pesquisaneonatos.cadastros_gerais.genero.dto.request.GeneroRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.dto.response.GeneroResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.service.GeneroService;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/genero")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping("/list")
    public ResponseEntity<List<ListagemCadastroResponse>> list() {
        var list = generoService.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/load/{id}")
    private ResponseEntity<GeneroResponse> load(@PathVariable Long id) {
        var response = generoService.load(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody GeneroRequest request) throws IOException {
        ApiResponseDTO response = generoService.save(request);
        return ResponseEntity.ok(response);
    }

}
