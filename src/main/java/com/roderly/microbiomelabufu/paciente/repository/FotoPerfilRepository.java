package com.roderly.microbiomelabufu.paciente.repository;

import com.roderly.microbiomelabufu.paciente.model.FotoPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FotoPerfilRepository extends JpaRepository<FotoPerfil, Long> {
    @Query("SELECT f.id_foto_perfil FROM FotoPerfil f WHERE f.paciente.id_paciente = :id_paciente")
    Optional<Long> findIdFotoPerfilByIdPaciente(@Param("id_paciente") Long id_paciente);
}
