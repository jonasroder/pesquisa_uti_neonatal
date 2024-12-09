package com.roderly.pesquisaneonatos.login.dto.response;

public record UserDataResponse(
        Long id_usuario,
        Long id_role,
        String usuario,
        String nome_completo
) {
}
