package com.roderly.microbiomelabufu.consulta.repository;

import com.roderly.microbiomelabufu.consulta.model.PrescricaoMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescricaoMedicamentoRepository   extends JpaRepository<PrescricaoMedicamento, Long> {
}
