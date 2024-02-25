package com.roderly.microbiomelabufu.consulta.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.consulta.dto.request.*;
import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaCompletaResponse;
import com.roderly.microbiomelabufu.consulta.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Transactional
@RequestMapping("/api/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/load/{id_paciente}/{id_consulta}")
    public ResponseEntity<ConsultaCompletaResponse> getDadosConsulta(@PathVariable Long id_paciente, @PathVariable Long id_consulta) {
        ConsultaCompletaResponse response = consultaService.getDadosConsulta(id_paciente, id_consulta);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponseDTO> save(@RequestBody @Valid ConsultaCompletoRequest request) throws IOException {
        ApiResponseDTO response = consultaService.saveConsulta(request);
        return ResponseEntity.ok(response);
    }

}
