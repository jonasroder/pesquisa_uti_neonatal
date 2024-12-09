package com.roderly.pesquisaneonatos.usuario.dto.response;

import com.roderly.pesquisaneonatos.login.dto.response.LoginLogDTO;

import java.util.List;

public record UsuarioResponseDTO (
        Long idUsuario,
        Long idRole,
        String usuario,
        String nomeCompleto,
        String telefone,
        String cpf,
        Boolean isActive,
        List<LoginLogDTO> loginLog
) {}
