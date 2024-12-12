package com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.controller;


import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.dto.request.ClassificacaoMicroorganismoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.dto.response.ClassificacaoMicroorganismoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.service.ClassificacaoMicroorganismoService;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/classificacao_microorganismo")
@RequiredArgsConstructor
public class ClassificacaoMicroorganismoController {

    private final ClassificacaoMicroorganismoService classificacaoMicroorganismoService;

    @GetMapping("/list")
    public ResponseEntity<List<ListagemCadastroResponse>> list() {
        var list = classificacaoMicroorganismoService.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/load/{id}")
    private ResponseEntity<ClassificacaoMicroorganismoResponse> load(@PathVariable Long id) {
        var response = classificacaoMicroorganismoService.load(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody ClassificacaoMicroorganismoRequest request) throws IOException {
        ApiResponseDTO response = classificacaoMicroorganismoService.save(request);
        return ResponseEntity.ok(response);
    }

}
