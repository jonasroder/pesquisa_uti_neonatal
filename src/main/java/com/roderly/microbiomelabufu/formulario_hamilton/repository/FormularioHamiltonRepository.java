package com.roderly.microbiomelabufu.formulario_hamilton.repository;

import com.roderly.microbiomelabufu.formulario_hamilton.model.FormularioHamilton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FormularioHamiltonRepository extends JpaRepository<FormularioHamilton, Long> {
    @Query("SELECT f FROM FormularioHamilton f WHERE f.consulta.id_consulta = :idConsulta")
    Optional<FormularioHamilton> findByConsulta_Id_Consulta(@Param("idConsulta") Long idConsulta);




}
