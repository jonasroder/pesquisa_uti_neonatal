package com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.controller;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.request.DiagonosticoRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.response.DiagnosticoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.service.DiagnosticoService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/diagnostico")
public class DiagnosticoController {
    @Autowired
    DiagnosticoService diagnosticoService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody DiagonosticoRequest request) throws IOException {
        ApiResponseDTO response = diagnosticoService.save(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<DiagnosticoResponse> loadById(@PathVariable Long id) {
        var response = diagnosticoService.loadById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list")
    private ResponseEntity<List<DiagnosticoResponse>> getList() {
        var listagem = diagnosticoService.getList();
        return ResponseEntity.ok(listagem);
    }
}