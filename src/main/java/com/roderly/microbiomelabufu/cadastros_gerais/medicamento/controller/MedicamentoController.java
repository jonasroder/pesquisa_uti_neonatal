package com.roderly.microbiomelabufu.cadastros_gerais.medicamento.controller;

import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.response.MedicamentoListResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.request.MedicamentoRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.response.MedicamentoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.service.MedicamentoService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/medicamento")
public class MedicamentoController {
    @Autowired
    MedicamentoService medicamentoService;


    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody MedicamentoRequest request) throws IOException{
        ApiResponseDTO response = medicamentoService.saveMedicamento(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/load/{id}")
    private ResponseEntity<MedicamentoResponse> getMedicamentoCadastro(@PathVariable Long id) {
        var response = medicamentoService.getDadosMedicamento(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list")
    private ResponseEntity<List<MedicamentoListResponse>> getListagemMedicamentos(){
        var listagem = medicamentoService.getDadosListagemMedicamentos();
        return ResponseEntity.ok(listagem);
    }

}
