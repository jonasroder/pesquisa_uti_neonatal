package com.roderly.pesquisaneonatos.usuario.controller;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.response.MedicamentoResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import com.roderly.pesquisaneonatos.usuario.dto.request.UsuarioCompletoRequest;
import com.roderly.pesquisaneonatos.usuario.dto.response.ListaUsuarioResponseDTO;
import com.roderly.pesquisaneonatos.usuario.dto.response.UsuarioResponseDTO;
import com.roderly.pesquisaneonatos.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/save")
    private ResponseEntity<ApiResponseDTO> save(@RequestBody @Valid UsuarioCompletoRequest request){
        ApiResponseDTO response = usuarioService.save(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/load/{id}")
    private ResponseEntity<UsuarioResponseDTO> getMedicamentoCadastro(@PathVariable Long id) {
        var response = usuarioService.load(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ListaUsuarioResponseDTO>> list() {
        var list = usuarioService.list();
        return ResponseEntity.ok(list);
    }
}
