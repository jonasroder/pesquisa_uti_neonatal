package com.roderly.pesquisaneonatos.usuario.dto.response;

public record ListaUsuarioResponseDTO(
        Long idUsuario,
        String nome,
        String email,
        String role,
        String ultimoLogin,
        Boolean isActive
) {
}
