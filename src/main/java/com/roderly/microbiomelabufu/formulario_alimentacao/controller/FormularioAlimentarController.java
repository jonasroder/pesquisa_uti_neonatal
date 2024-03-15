package com.roderly.microbiomelabufu.formulario_alimentacao.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.formulario_alimentacao.dto.request.FormularioAlimentarRequest;
import com.roderly.microbiomelabufu.formulario_alimentacao.dto.response.FormularioAlimentarPageResponse;
import com.roderly.microbiomelabufu.formulario_alimentacao.service.FormularioAlimentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/formulario_alimentar")
public class FormularioAlimentarController {
    @Autowired
    FormularioAlimentarService formularioAlimentarService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody FormularioAlimentarRequest request) throws IOException {
        ApiResponseDTO response = formularioAlimentarService.save(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/load/{id_consulta}")
    private ResponseEntity<FormularioAlimentarPageResponse> load(@PathVariable Long id_consulta) {
        var response = formularioAlimentarService.load(id_consulta);
        return ResponseEntity.ok(response);
    }

}
