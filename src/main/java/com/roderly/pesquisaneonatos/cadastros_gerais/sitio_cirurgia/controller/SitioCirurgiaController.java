package com.roderly.pesquisaneonatos.cadastros_gerais.sitio_cirurgia.controller;

import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_cirurgia.service.SitioCirurgiaService;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/sitio_cirurgia")
@RequiredArgsConstructor
public class SitioCirurgiaController {

    private final SitioCirurgiaService service;

    @GetMapping("/list")
    public ResponseEntity<List<ListagemCadastroResponse>> list() {
        var list = service.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/load/{id}")
    private ResponseEntity<CadastroResponse> load(@PathVariable Long id) {
        var response = service.load(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody CadastroRequest request) throws IOException {
        ApiResponseDTO response = service.save(request);
        return ResponseEntity.ok(response);
    }

}
