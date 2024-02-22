package com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.repository;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}
