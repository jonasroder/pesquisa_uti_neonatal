package com.roderly.pesquisaneonatos.neonato.repository;

import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.EventoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.ClasseAntimicrobianoCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NeonatoRepository extends JpaRepository<Neonato, Long>, JpaSpecificationExecutor<Neonato> {

    Optional<Neonato> findByProntuario(String prontuario);


    @Query(value = """
            SELECT te.id_tipo_evento AS idTipoEvento,
                   te.descricao AS evento,
                   COUNT(e.id_evento) AS nEventos
            FROM evento e
            JOIN tipo_evento te ON te.id_tipo_evento = e.id_tipo_evento
            WHERE e.id_neonato = :idNeonato
            AND e.is_active = true
            GROUP BY e.id_neonato, te.id_tipo_evento
            """, nativeQuery = true)
    List<EventoCountProjection> findEventoCountsByNeonato(@Param("idNeonato") Long idNeonato);


    @Query(value = """
            SELECT COUNT(DISTINCT e.data_evento) AS nEventos, ca.id_classe_antimicrobiano AS idClasseAntimicrobiano, ca.descricao AS classeAntimicrobiano
            FROM evento_entidade ee
            JOIN evento e ON e.id_evento = ee.id_evento
            JOIN antimicrobiano a ON a.id_antimicrobiano = ee.id_entidade
            JOIN classe_antimicrobiano ca ON ca.id_classe_antimicrobiano = a.id_classe_antimicrobiano
            WHERE ee.tipo_entidade = 'antimicrobiano'
            AND e.is_active = true
            AND e.id_neonato = :idNeonato
            GROUP BY ca.id_classe_antimicrobiano
            """, nativeQuery = true)
    List<ClasseAntimicrobianoCountProjection> findClasseAntimicrobianoCountsByNeonato(@Param("idNeonato") Long idNeonato);


    @Query(value = """            
            SELECT COALESCE(COUNT(DISTINCT e.data_evento), 0) AS nEventos
            FROM evento_entidade ee
            JOIN evento e ON e.id_evento = ee.id_evento
            JOIN antimicrobiano a ON a.id_antimicrobiano = ee.id_entidade\s
            JOIN classe_antimicrobiano ca ON ca.id_classe_antimicrobiano = a.id_classe_antimicrobiano\s
            WHERE ee.tipo_entidade = 'antimicrobiano'
            AND e.is_active = true
            AND e.id_neonato = :idNeonato
            AND ca.id_tipo_antimicrobiano = :idTipoAntimicrobiano
            """, nativeQuery = true)
    Long getDiasUsoAntimicrobiano(@Param("idNeonato") Long idNeonato, @Param("idTipoAntimicrobiano") Long idTipoAntimicrobiano);


}

