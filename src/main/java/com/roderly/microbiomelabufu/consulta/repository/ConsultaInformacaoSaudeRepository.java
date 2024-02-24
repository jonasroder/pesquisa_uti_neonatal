package com.roderly.microbiomelabufu.consulta.repository;

import com.roderly.microbiomelabufu.consulta.model.ConsultaInformacaoSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultaInformacaoSaudeRepository extends JpaRepository<ConsultaInformacaoSaude, Long> {
    @Query("SELECT pm FROM ConsultaInformacaoSaude pm WHERE pm.consulta.id_consulta = :idConsulta AND pm.is_active = true")
    List<ConsultaInformacaoSaude> findByIdConsulta(Long idConsulta);
}
