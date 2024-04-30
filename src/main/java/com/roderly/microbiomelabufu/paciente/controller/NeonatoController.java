package com.roderly.microbiomelabufu.paciente.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/neonato")
@RequiredArgsConstructor
public class NeonatoController {

//    private final PacienteService pacienteService;
//
//
//    @PostMapping("/save")
//    public ResponseEntity<ApiResponseDTO> insert(@RequestBody @Valid PacienteCompletoRequest request) throws IOException {
//        ApiResponseDTO response = pacienteService.savePaciente(request);
//        return ResponseEntity.ok(response);
//    }
//
//
//    @GetMapping("/load/{id}")
//    public ResponseEntity<PacienteCompletoResponse> getPacienteCadastro(@PathVariable Long id) {
//        PacienteCompletoResponse response = pacienteService.getDadosPaciente(id);
//        return ResponseEntity.ok(response);
//    }
//
//
//    @GetMapping("/list")
//    public ResponseEntity<Set<PacienteListagemResponse>> getListagemPaciente() {
//        Set<PacienteListagemResponse> listagem = pacienteService.getDadosListagemPaciente();
//        return ResponseEntity.ok(listagem);
//    }
//
//
//    @GetMapping("/get_info_basica/{id}")
//    public ResponseEntity<PacienteInfoBasicaResponse> getInfoBasicaPaciente(@PathVariable Long id) {
//        var response = pacienteService.getInfoBasicaPaciente(id);
//        return ResponseEntity.ok(response);
//    }
}
