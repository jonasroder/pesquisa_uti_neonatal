package com.roderly.pesquisaneonatos.prontuario.repository;

import com.roderly.pesquisaneonatos.prontuario.model.AntibiogramaIsolado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AntibiogramaIsoladoRepository extends JpaRepository<AntibiogramaIsolado, Long> {

    @Query("SELECT a FROM AntibiogramaIsolado a WHERE a.isoladoColeta.idIsoladoColeta = :idIsoladoColeta")
    List<AntibiogramaIsolado> findByIsoladoColetaId(@Param("idIsoladoColeta") Long idIsoladoColeta);

}
