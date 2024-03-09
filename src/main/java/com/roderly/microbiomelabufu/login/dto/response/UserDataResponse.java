package com.roderly.microbiomelabufu.login.dto.response;

public record UserDataResponse(
        Long id_usuario,
        String usuario,
        String nome_completo
) {
}
