package com.roderly.microbiomelabufu.cadastros_gerais.medicamento.repository;

import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
