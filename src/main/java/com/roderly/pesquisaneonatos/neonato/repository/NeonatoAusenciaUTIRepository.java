package com.roderly.pesquisaneonatos.neonato.repository;

import com.roderly.pesquisaneonatos.neonato.dto.projections.DiasAusenciaUTIProjection;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.neonato.model.NeonatoAusenciaUTI;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.EventoCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NeonatoAusenciaUTIRepository extends JpaRepository<NeonatoAusenciaUTI, Long> {

    @Query(value = """
            
            SELECT  nau.data_saida_uti as dataSaida,
                    nau.data_retorno_uti as dataRetorno
            FROM neonato_ausencia_uti nau
            WHERE nau.id_neonato = :idNeonato
            AND nau.data_saida_uti IS NOT NULL
            AND nau.data_retorno_uti IS NOT NULL
            """, nativeQuery = true)
    List<DiasAusenciaUTIProjection> findDiasAusenciaUTI(@Param("idNeonato") Long idNeonato);
}


