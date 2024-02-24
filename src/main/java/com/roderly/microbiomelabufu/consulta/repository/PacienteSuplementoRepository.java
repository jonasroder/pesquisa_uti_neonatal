package com.roderly.microbiomelabufu.consulta.repository;

import com.roderly.microbiomelabufu.consulta.model.PacienteSuplemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteSuplementoRepository  extends JpaRepository<PacienteSuplemento, Long> {
    @Query("SELECT pm FROM PacienteSuplemento pm WHERE pm.consulta.id_consulta = :idConsulta AND pm.is_active = true")
    List<PacienteSuplemento> findByIdConsulta(Long idConsulta);
}
