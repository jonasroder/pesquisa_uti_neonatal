package com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.controller;


import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.dto.request.MotivoInternacaoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.dto.response.MotivoInternacaoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.service.MotivoInternacaoService;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/motivo_internacao")
@RequiredArgsConstructor
public class MotivoInternacaoController {

    private final MotivoInternacaoService motivoInternacaoService;

    @GetMapping("/list")
    public ResponseEntity<List<ListagemCadastroResponse>> list() {
        var list = motivoInternacaoService.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/load/{id}")
    private ResponseEntity<MotivoInternacaoResponse> load(@PathVariable Long id) {
        var response = motivoInternacaoService.load(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody MotivoInternacaoRequest request) throws IOException {
        ApiResponseDTO response = motivoInternacaoService.save(request);
        return ResponseEntity.ok(response);
    }

}
