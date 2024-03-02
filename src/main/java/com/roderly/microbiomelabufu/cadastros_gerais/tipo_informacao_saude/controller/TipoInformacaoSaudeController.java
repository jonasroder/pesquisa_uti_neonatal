package com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.controller;

import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.dto.request.TipoInformacaoSaudeRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.dto.resopnse.TipoInformacaoSaudeResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.service.TipoInformacaoSaudeService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tipo_info_saude")
public class TipoInformacaoSaudeController {
    @Autowired
    TipoInformacaoSaudeService tipoInformacaoSaudeService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody TipoInformacaoSaudeRequest request) throws IOException {
        ApiResponseDTO response = tipoInformacaoSaudeService.save(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<TipoInformacaoSaudeResponse> loadById(@PathVariable Long id) {
        var response = tipoInformacaoSaudeService.loadById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list")
    private ResponseEntity<List<TipoInformacaoSaudeResponse>> getList() {
        var listagem = tipoInformacaoSaudeService.getList();
        return ResponseEntity.ok(listagem);
    }
}