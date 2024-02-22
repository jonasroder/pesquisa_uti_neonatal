package com.roderly.microbiomelabufu.usuario.repository;

import com.roderly.microbiomelabufu.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    UserDetails findByUsuario(String usuario);
}
