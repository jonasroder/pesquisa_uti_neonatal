package com.roderly.microbiomelabufu.usuario.service;

import com.roderly.microbiomelabufu.usuario.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    public static Long getIdUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            Usuario usuario = (Usuario) authentication.getPrincipal();
            return usuario.getId_usuario();
        }

        throw new IllegalStateException("Usuário não autenticado ou tipo de Principal não suportado.");
    }


    public static Usuario getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            Usuario usuario = (Usuario) authentication.getPrincipal();
            return usuario;
        }

        throw new IllegalStateException("Usuário não autenticado ou tipo de Principal não suportado.");
    }
}
