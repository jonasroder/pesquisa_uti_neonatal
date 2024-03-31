package com.roderly.microbiomelabufu.agenda.repository;

import com.roderly.microbiomelabufu.agenda.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository <Agenda, Long> {
    @Query("SELECT a FROM Agenda a WHERE a.usuario.id_usuario = :idUsuario")
    List<Agenda> findByUsuarioId(Long idUsuario);
}
