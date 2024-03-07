package com.roderly.microbiomelabufu.formulario_hamilton.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.request.FormularioHamiltonRequest;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonReponse;
import com.roderly.microbiomelabufu.formulario_hamilton.service.FormularioHamiltonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/formulario_hamilton")
public class FormularioHamiltonController {
    @Autowired
    FormularioHamiltonService formularioHamiltonService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody FormularioHamiltonRequest request) throws IOException {
        ApiResponseDTO response = formularioHamiltonService.save(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/load/{id_consulta}")
    private ResponseEntity<FormularioHamiltonReponse> load(@PathVariable Long id_consulta) {
        var response = formularioHamiltonService.load(id_consulta);
        return ResponseEntity.ok(response);
    }


}
