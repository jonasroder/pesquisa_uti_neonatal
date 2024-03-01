package com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.controller;

import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.request.IngredienteRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.response.IngredienteResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.service.IngredienteService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/ingrediente")
public class IngredienteController {
    @Autowired
    IngredienteService ingredienteService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody IngredienteRequest request) throws IOException {
        ApiResponseDTO response = ingredienteService.save(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/load/{id_suplemento}")
    public ResponseEntity<IngredienteResponse> getDadosConsulta(@PathVariable Long id_suplemento) {
        IngredienteResponse response = ingredienteService.getDadosIngrediente(id_suplemento);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    private ResponseEntity<List<IngredienteResponse>> getListagemMedicamentos(){
        var listagem = ingredienteService.getDadosListagemIngredientes();
        return ResponseEntity.ok(listagem);
    }

}
