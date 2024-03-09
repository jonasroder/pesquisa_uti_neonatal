package com.roderly.microbiomelabufu.login.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roderly.microbiomelabufu.login.dto.response.UserDataResponse;
import com.roderly.microbiomelabufu.usuario.model.Usuario;

import java.io.IOException;

public class AuthenticationMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static UserDataResponse usuarioToUserDataResponse(Usuario usuario){
        return new UserDataResponse(
                usuario.getId_usuario(),
                usuario.getUsuario(),
                usuario.getNome_completo()
        );
    }


    public static String jsonUserDataFrontToUserNameRequest(String userDataJson) {
        try {
            UserDataResponse userDataResponse = objectMapper.readValue(userDataJson, UserDataResponse.class);
            return userDataResponse.usuario();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao deserializar o JSON do usuário", e);
        }
    }
}
