package com.roderly.microbiomelabufu.consulta.repository;

import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteMedicamentoRepository  extends JpaRepository<PacienteMedicamento, Long> {
}
