package com.roderly.microbiomelabufu.diagnostico.repository;

import com.roderly.microbiomelabufu.diagnostico.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}
