package com.roderly.pesquisaneonatos.usuario.mappper;

import com.roderly.pesquisaneonatos.usuario.dto.request.UsuarioCompletoRequest;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;

public class UsuarioMapper {
    public static Usuario usuarioCompletoRequestToUsuario(UsuarioCompletoRequest user, String encryptedPassword){
        Usuario usuario = new Usuario();

        usuario.setId_usuario(user.id_usuario());
        usuario.setUsuario(user.usuario());
        usuario.setSenha(encryptedPassword);
        usuario.setNome_completo(user.nome_completo());
        usuario.setTelefone_1(user.telefone_1());
        usuario.setTelefone_2(user.telefone_2());
        usuario.setCpf(user.cpf());

        return usuario;
    }
}
