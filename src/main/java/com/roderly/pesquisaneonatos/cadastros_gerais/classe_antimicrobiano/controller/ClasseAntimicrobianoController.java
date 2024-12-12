package com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.controller;


import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.dto.request.ClasseAntimicrobianoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.dto.response.ClasseAntimicrobianoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.service.ClasseAntimicrobianoService;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/classe_antimicrobiano")
@RequiredArgsConstructor
public class ClasseAntimicrobianoController {

    private final ClasseAntimicrobianoService classeAntimicrobianoService;

    @GetMapping("/list")
    public ResponseEntity<List<ListagemCadastroResponse>> list() {
        var list = classeAntimicrobianoService.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/load/{id}")
    private ResponseEntity<ClasseAntimicrobianoResponse> load(@PathVariable Long id) {
        var response = classeAntimicrobianoService.load(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody ClasseAntimicrobianoRequest request) throws IOException {
        ApiResponseDTO response = classeAntimicrobianoService.save(request);
        return ResponseEntity.ok(response);
    }

}
