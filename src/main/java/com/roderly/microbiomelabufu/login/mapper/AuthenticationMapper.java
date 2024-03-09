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


    public static Usuario jsonUserDataFrontToUsuario(String userDataJson) {
        try {
            var userDataResponse = objectMapper.readValue(userDataJson, UserDataResponse.class);
            var usuario = new Usuario();

            usuario.setId_usuario(userDataResponse.id_usuario());
            usuario.setUsuario(userDataResponse.usuario());
            usuario.setNome_completo(userDataResponse.nome_completo());

            return usuario;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao deserializar o JSON do usuário", e);
        }
    }
}
