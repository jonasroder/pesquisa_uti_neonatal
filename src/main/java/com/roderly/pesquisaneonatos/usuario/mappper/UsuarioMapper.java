package com.roderly.pesquisaneonatos.usuario.mappper;

import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.Utilitarios.ObjectUtils;
import com.roderly.pesquisaneonatos.common.Utilitarios.StringUtils;
import com.roderly.pesquisaneonatos.login.dto.response.LoginLogDTO;
import com.roderly.pesquisaneonatos.login.model.LoginLog;
import com.roderly.pesquisaneonatos.usuario.dto.request.UsuarioCompletoRequest;
import com.roderly.pesquisaneonatos.usuario.dto.response.ListaUsuarioResponseDTO;
import com.roderly.pesquisaneonatos.usuario.dto.response.UsuarioResponseDTO;
import com.roderly.pesquisaneonatos.usuario.model.Role;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;

import java.util.Comparator;

public class UsuarioMapper {
    public static Usuario usuarioCompletoRequestToUsuario(UsuarioCompletoRequest user, String encryptedPassword){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(user.idUsuario());
        usuario.setRole(new Role(user.idRole()));
        usuario.setUsuario(user.usuario());

        if(encryptedPassword != null){
            usuario.setSenha(encryptedPassword);
        }
        usuario.setNomeCompleto(user.nomeCompleto());
        usuario.setTelefone(StringUtils.removerMascara(user.telefone()));
        usuario.setCpf(StringUtils.removerMascara(user.cpf()));
        usuario.setIsActive(user.isActive());

        return usuario;
    }


    public static LoginLogDTO logToLoginLogDTO(LoginLog loginLog){
        return new LoginLogDTO(
                DateUtil.LocalDateTimeToDateTimeBR(loginLog.getDataHora()),
                loginLog.getIpAddress(),
                loginLog.getStatus(),
                loginLog.getUserAgent()
        );
    }


    public static UsuarioResponseDTO usuarioToUsuarioResponseDTO(Usuario usuario){
        var listaLog = usuario.getLoginLogs().stream()
                .map(UsuarioMapper::logToLoginLogDTO)
                .toList();

        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                ObjectUtils.safeGet(usuario.getRole(), Role::getIdRole),
                usuario.getUsuario(),
                usuario.getNomeCompleto(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getIsActive(),
                listaLog
                );
    }


    public static ListaUsuarioResponseDTO usuarioToListaUsuarioResponseDTO(Usuario usuario) {

        var ultimoLogin = usuario.getLoginLogs().stream()
                .max(Comparator.comparing(LoginLog::getIdLoginLog))
                .map(LoginLog::getDataHora)
                .orElse(null);

        return new ListaUsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNomeCompleto(),
                usuario.getUsuario(),
                usuario.getRole().getNome(),
                DateUtil.LocalDateTimeToDateTimeBR(ultimoLogin),
                usuario.getIsActive()
        );
    }

}
