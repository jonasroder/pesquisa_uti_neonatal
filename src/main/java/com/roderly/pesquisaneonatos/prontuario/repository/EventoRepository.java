package com.roderly.pesquisaneonatos.prontuario.repository;

import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByNeonatoIdNeonato(Long idNeonato);
}
