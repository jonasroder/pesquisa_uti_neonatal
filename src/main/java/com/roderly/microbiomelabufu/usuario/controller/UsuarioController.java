package com.roderly.microbiomelabufu.usuario.controller;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.usuario.dto.request.UsuarioCompletoRequest;
import com.roderly.microbiomelabufu.usuario.mappper.UsuarioMapper;
import com.roderly.microbiomelabufu.usuario.model.Usuario;
import com.roderly.microbiomelabufu.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping("/insert")
    public ResponseEntity<ApiResponseDTO> insert(@RequestBody @Valid UsuarioCompletoRequest userRequest) {

        if (this.usuarioRepository.findByUsuario(userRequest.usuario()) != null) {
            throw new EntityNotFoundException("Nome de Usuário Não Disponível!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequest.senha());
        Usuario novoUsuraio = UsuarioMapper.usuarioCompletoRequestToUsuario(userRequest,encryptedPassword);
        usuarioRepository.save(novoUsuraio);

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuraio);

        return ResponseEntity.ok(new ApiResponseDTO( (long) novoUsuraio.getId_usuario() ,"Usuário cadastrado com sucesso."));
    }
}
