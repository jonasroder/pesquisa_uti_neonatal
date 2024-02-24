package com.roderly.microbiomelabufu.consulta.repository;

import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteMedicamentoRepository  extends JpaRepository<PacienteMedicamento, Long> {
    @Query("SELECT pm FROM PacienteMedicamento pm WHERE pm.consulta.id_consulta = :idConsulta AND pm.is_active = true")
    List<PacienteMedicamento> findByIdConsulta(Long idConsulta);
}
