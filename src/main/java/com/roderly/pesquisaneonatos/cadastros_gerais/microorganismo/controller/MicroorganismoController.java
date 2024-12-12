package com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.controller;


import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.dto.request.MicroorganismoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.dto.response.MicroorganismoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.service.MicroorganismoService;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/microorganismo")
@RequiredArgsConstructor
public class MicroorganismoController {

    private final MicroorganismoService microorganismoService;

    @GetMapping("/list")
    public ResponseEntity<List<ListagemCadastroResponse>> list() {
        var list = microorganismoService.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/load/{id}")
    private ResponseEntity<MicroorganismoResponse> load(@PathVariable Long id) {
        var response = microorganismoService.load(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody MicroorganismoRequest request) throws IOException {
        ApiResponseDTO response = microorganismoService.save(request);
        return ResponseEntity.ok(response);
    }

}
