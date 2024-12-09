package com.roderly.pesquisaneonatos.login.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roderly.pesquisaneonatos.common.Utilitarios.ObjectUtils;
import com.roderly.pesquisaneonatos.login.dto.response.UserDataResponse;
import com.roderly.pesquisaneonatos.usuario.model.Role;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;

import java.io.IOException;

public class AuthenticationMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static UserDataResponse usuarioToUserDataResponse(Usuario usuario){
        return new UserDataResponse(
                usuario.getIdUsuario(),
                ObjectUtils.safeGet(usuario.getRole(), Role::getIdRole),
                usuario.getUsuario(),
                usuario.getNomeCompleto()
        );
    }


    public static Usuario jsonUserDataFrontToUsuario(String userDataJson) {
        try {
            var userDataResponse = objectMapper.readValue(userDataJson, UserDataResponse.class);
            var usuario = new Usuario();

            usuario.setIdUsuario(userDataResponse.id_usuario());
            usuario.setUsuario(userDataResponse.usuario());
            usuario.setNomeCompleto(userDataResponse.nome_completo());

            return usuario;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao deserializar o JSON do usuário", e);
        }
    }
}
