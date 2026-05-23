package com.roderly.pesquisaneonatos.usuario.repository;

import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u.isActive FROM Usuario u WHERE u.idUsuario = :idUsuario")
    Boolean isUsuarioAtivo(@Param("idUsuario") Long idUsuario);

    Usuario findByUsuario(String usuario);

    Usuario findByCpf(String cpf);

    List<Usuario> findAllByIsActiveTrue();
}
