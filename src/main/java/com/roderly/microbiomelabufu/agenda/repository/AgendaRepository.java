package com.roderly.microbiomelabufu.agenda.repository;

import com.roderly.microbiomelabufu.agenda.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository <Agenda, Long> {
}
