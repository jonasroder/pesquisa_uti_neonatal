package com.roderly.pesquisaneonatos.prontuario.controller;


import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.prontuario.service.ProntuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prontuario")
@RequiredArgsConstructor
public class ProntuarioController {

    private final ProntuarioService prontuarioService;


    @GetMapping("/load/{id}")
    public ResponseEntity<String> load(@PathVariable Long id) {
        //var response = prontuarioService.load(id);
        return ResponseEntity.ok("txt");
    }

}
