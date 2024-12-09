package com.roderly.pesquisaneonatos.usuario.service;

import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.usuario.dto.request.UsuarioCompletoRequest;
import com.roderly.pesquisaneonatos.usuario.dto.response.ListaUsuarioResponseDTO;
import com.roderly.pesquisaneonatos.usuario.dto.response.UsuarioResponseDTO;
import com.roderly.pesquisaneonatos.usuario.mappper.UsuarioMapper;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import com.roderly.pesquisaneonatos.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public ApiResponseDTO save(UsuarioCompletoRequest request) {

        if (request.idUsuario() == null && usuarioRepository.findByUsuario(request.usuario()) != null) {
            return ApiResponseDTO.failure("Email já cadastrado!");
        }
        if (request.idUsuario() == null && usuarioRepository.findByCpf(request.cpf()) != null) {
            return ApiResponseDTO.failure("CPF já cadastrado!");
        }

        if (request.idUsuario() == null && request.senha() == null) {
            return ApiResponseDTO.failure("Senha é obrigatória para novo cadastro!");
        }

        Usuario usuario = null;
        if (request.idUsuario() != null) {
            usuario = usuarioRepository.findById(request.idUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        }

        String encryptedPassword = (request.senha() != null)
                ? new BCryptPasswordEncoder().encode(request.senha())
                : usuario.getSenha();

        Usuario novoUsuraio = UsuarioMapper.usuarioCompletoRequestToUsuario(request, encryptedPassword);
        Usuario usuarioSalvo = usuarioRepository.save(novoUsuraio);

        return new ApiResponseDTO(usuarioSalvo.getIdUsuario(), "O registro foi Salvo!");
    }


    public UsuarioResponseDTO load(Long id) {
        var medicamento = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return UsuarioMapper.usuarioToUsuarioResponseDTO(medicamento);
    }


    public List<ListaUsuarioResponseDTO> list() {
        var usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioMapper::usuarioToListaUsuarioResponseDTO).toList();
    }

}
