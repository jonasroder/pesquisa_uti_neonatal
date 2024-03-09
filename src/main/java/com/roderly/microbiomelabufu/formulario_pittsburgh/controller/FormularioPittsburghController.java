package com.roderly.microbiomelabufu.formulario_pittsburgh.controller;


import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.request.FormularioHamiltonRequest;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.request.FormularioPittsburghRequest;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.response.FormularioPittsburghPageResponse;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.response.FormularioPittsburghResponse;
import com.roderly.microbiomelabufu.formulario_pittsburgh.service.FormularioPittsburghService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/formulario_pittsburgh")
public class FormularioPittsburghController {
    @Autowired
    FormularioPittsburghService formularioPittsburghService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody FormularioPittsburghRequest request) throws IOException {
        ApiResponseDTO response = formularioPittsburghService.save(request);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/load/{id_consulta}")
    private ResponseEntity<FormularioPittsburghPageResponse> load(@PathVariable Long id_consulta) {
        var response = formularioPittsburghService.load(id_consulta);
        return ResponseEntity.ok(response);
    }

}
