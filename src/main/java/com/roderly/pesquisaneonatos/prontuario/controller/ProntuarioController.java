package com.roderly.pesquisaneonatos.prontuario.controller;


import com.roderly.pesquisaneonatos.common.dto.ApiResponseDTO;
import com.roderly.pesquisaneonatos.prontuario.dto.request.EventoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ProntuarioResponse;
import com.roderly.pesquisaneonatos.prontuario.service.ProntuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/prontuario")
@RequiredArgsConstructor
public class ProntuarioController {

    private final ProntuarioService prontuarioService;


    @GetMapping("/load/{id}")
    public ResponseEntity<ProntuarioResponse> load(@PathVariable Long id) {
        var response = prontuarioService.load(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save_evento")
    public ResponseEntity<ApiResponseDTO> salvarEvento(@RequestBody EventoRequest request) throws IOException {
        ApiResponseDTO response = prontuarioService.salvarEvento(request);
        return ResponseEntity.ok(response);
    }

}
