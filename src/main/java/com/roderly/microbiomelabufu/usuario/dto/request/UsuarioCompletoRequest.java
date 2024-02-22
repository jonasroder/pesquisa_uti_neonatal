package com.roderly.microbiomelabufu.usuario.dto.request;


public record UsuarioCompletoRequest(
        Long id_usuario,
        String usuario,
        String senha,
        String nome_completo,
        String telefone_1,
        String telefone_2,
        String cpf,
        Boolean is_active
) {}