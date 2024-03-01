package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.controller;

import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.request.SuplementoPageRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoListagemResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoPageResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.service.SuplementoService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/suplemento")
public class SuplementoController {

    @Autowired
    SuplementoService suplementoService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody SuplementoPageRequest request) throws IOException{
        ApiResponseDTO response = suplementoService.saveSuplemento(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/load/{id_suplemento}")
    public ResponseEntity<SuplementoPageResponse> getDadosConsulta(@PathVariable Long id_suplemento) {
        SuplementoPageResponse response = suplementoService.getDadosSuplemento(id_suplemento);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    private ResponseEntity<List<SuplementoListagemResponse>> getListagemMedicamentos(){
        var listagem = suplementoService.getDadosListagemSuplementos();
        return ResponseEntity.ok(listagem);
    }
}
