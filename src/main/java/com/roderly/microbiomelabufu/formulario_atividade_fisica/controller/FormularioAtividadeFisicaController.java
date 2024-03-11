package com.roderly.microbiomelabufu.formulario_atividade_fisica.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.request.FormularioAtividadeFisicaRequest;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.response.FormularioAtividadeFisicaPageResponse;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.service.FormularioAtividadeFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/formulario_atividade_fisica")
public class FormularioAtividadeFisicaController {
    @Autowired
    FormularioAtividadeFisicaService formularioAtividadeFisicaService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody FormularioAtividadeFisicaRequest request) throws IOException {
        ApiResponseDTO response = formularioAtividadeFisicaService.save(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/load/{id_consulta}")
    private ResponseEntity<FormularioAtividadeFisicaPageResponse> load(@PathVariable Long id_consulta) {
        var response = formularioAtividadeFisicaService.load(id_consulta);
        return ResponseEntity.ok(response);
    }


}
