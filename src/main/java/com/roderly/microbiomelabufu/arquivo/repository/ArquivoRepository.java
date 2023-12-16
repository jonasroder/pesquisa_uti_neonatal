package com.roderly.microbiomelabufu.arquivo.repository;

import com.roderly.microbiomelabufu.arquivo.model.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    @Query("SELECT a FROM Arquivo a WHERE a.entidade = :entidade AND a.id_entidade = :id_entidade")
    Optional<Arquivo> findByEntidadeAndId_entidade(@Param("entidade") String entidade, @Param("id_entidade") Long id_entidade);



}
