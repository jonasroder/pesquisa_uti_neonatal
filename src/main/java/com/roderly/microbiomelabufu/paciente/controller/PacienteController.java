package com.roderly.microbiomelabufu.paciente.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.paciente.dto.request.PacienteCompletoRequest;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteFormularioResponse;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteInfoBasicaResponse;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteListagemResponse;
import com.roderly.microbiomelabufu.paciente.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/api/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;


    @PostMapping("/save")
    public ResponseEntity<ApiResponseDTO> insert(@RequestBody @Valid PacienteCompletoRequest request) throws IOException {
        ApiResponseDTO response = pacienteService.savePaciente(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<PacienteCompletoResponse> getPacienteCadastro(@PathVariable Long id) {
        PacienteCompletoResponse response = pacienteService.getDadosPaciente(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list")
    public ResponseEntity<Set<PacienteListagemResponse>> getListagemPaciente() {
        Set<PacienteListagemResponse> listagem = pacienteService.getDadosListagemPaciente();
        return ResponseEntity.ok(listagem);
    }


    @GetMapping("/get_info_basica/{id}")
    public ResponseEntity<PacienteInfoBasicaResponse> getInfoBasicaPaciente(@PathVariable Long id) {
        var response = pacienteService.getInfoBasicaPaciente(id);
        return ResponseEntity.ok(response);
    }
}
