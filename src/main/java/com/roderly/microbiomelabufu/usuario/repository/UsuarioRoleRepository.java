package com.roderly.microbiomelabufu.usuario.repository;

import com.roderly.microbiomelabufu.usuario.model.Usuario;
import com.roderly.microbiomelabufu.usuario.model.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Long> {
    @Query("SELECT ur.usuario FROM UsuarioRole ur WHERE ur.role.id_role IN :roleIds")
    List<Usuario> findUsuariosByRoleIds(@Param("roleIds") Set<Long> roleIds);
}
