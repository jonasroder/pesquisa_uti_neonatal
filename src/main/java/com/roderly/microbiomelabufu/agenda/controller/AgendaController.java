package com.roderly.microbiomelabufu.agenda.controller;

import com.roderly.microbiomelabufu.agenda.dto.request.AgendaRequest;
import com.roderly.microbiomelabufu.agenda.dto.response.AgendaResponse;
import com.roderly.microbiomelabufu.agenda.dto.response.UsuariosAgendaReponse;
import com.roderly.microbiomelabufu.agenda.service.AgendaService;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteCompletoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDTO> save(@RequestBody AgendaRequest request) throws IOException {
        var response = agendaService.save(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/load_users_calendar")
    public ResponseEntity<List<UsuariosAgendaReponse>> getPacienteCadastro() {
        var response = agendaService.getUsuariosAgenda();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/load_calendar_events/{id}")
    public ResponseEntity<List<AgendaResponse>> loadEvents(@PathVariable Long id) {
        var response = agendaService.loadEvents(id);
        return ResponseEntity.ok(response);
    }

}
