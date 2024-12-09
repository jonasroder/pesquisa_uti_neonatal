package com.roderly.pesquisaneonatos.usuario.dto.request;


public record UsuarioCompletoRequest(
        Long idUsuario,
        Long idRole,
        String usuario,
        String senha,
        String nomeCompleto,
        String telefone,
        String cpf,
        Boolean isActive
) {}