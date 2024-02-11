package com.roderly.microbiomelabufu.login.dto;


public record RegisterDTO(
        String usuario,
        String senha,
        String nome_completo,
        String telefone_1,
        String telefone_2,
        String cpf
) {}