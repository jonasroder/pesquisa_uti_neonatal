package com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.controller;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.request.MedicamentoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.response.MedicamentoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.service.MedicamentoService;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/medicamento")
@RequiredArgsConstructor
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @GetMapping("/list")
    public ResponseEntity<List<ListagemCadastroResponse>> list() {
        var list = medicamentoService.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/load/{id}")
    private ResponseEntity<MedicamentoResponse> getMedicamentoCadastro(@PathVariable Long id) {
        var response = medicamentoService.load(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody MedicamentoRequest request) throws IOException {
        ApiResponseDTO response = medicamentoService.save(request);
        return ResponseEntity.ok(response);
    }

}
