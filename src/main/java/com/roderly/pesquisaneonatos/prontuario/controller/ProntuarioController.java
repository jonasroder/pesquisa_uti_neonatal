package com.roderly.pesquisaneonatos.prontuario.controller;


import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.prontuario.dto.request.ColetaIsoladoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.request.EventoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ProntuarioResponse;
import com.roderly.pesquisaneonatos.prontuario.service.ProntuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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


    @GetMapping("/load_coletas_prontuario/{id}")
    public ResponseEntity<List<ColetaIsoladoResponse>> loadColetasProntuario(@PathVariable Long id) {
        var response = prontuarioService.loadColetasProntuario(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save_coleta")
    public ResponseEntity<ApiResponseDTO> salvarColetaIsolado(@RequestBody List<ColetaIsoladoRequest> request) throws IOException {
        ApiResponseDTO response = prontuarioService.salvarColetaIsolado(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/excluir_evento/{id}")
    public ResponseEntity<ApiResponseDTO> excluirEvento(@PathVariable Long id) throws IOException {
        ApiResponseDTO response = prontuarioService.excluirEvento(id);
        return ResponseEntity.ok(response);
    }

}
