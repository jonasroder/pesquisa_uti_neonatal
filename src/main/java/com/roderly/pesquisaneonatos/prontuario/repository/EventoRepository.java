package com.roderly.pesquisaneonatos.prontuario.repository;

import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoSemAntibiogramasResponse;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import com.roderly.pesquisaneonatos.prontuario.model.IsoladoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByNeonatoIdNeonatoAndIsActiveTrue(Long idNeonato);


    @Query("SELECT new com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoSemAntibiogramasResponse( " +
            "e.idEvento, " +
            "ee.idEventoEntidade, " +
            "e.dataEvento, " +
            "e.observacao, " +
            "sc.idSitioColeta, " +
            "sc.descricao, " +
            "ic.idIsoladoColeta, " +
            "ic.microorganismo.idMicroorganismo, " +
            "ic.perfilResistenciaMicroorganismo.idPerfilResistenciaMicroorganismo, " +
            "ic.mecanismoResistenciaMicroorganismo.idMecanismoResistenciaMicroorganismo, " +
            "ic.desconsiderarColeta, " +
            "CASE WHEN m.classificacaoMicroorganismo.idClassificacaoMicroorganismo = 3 THEN true ELSE false END) " +
            "FROM Evento e " +
            "JOIN EventoEntidade ee ON ee.evento.idEvento = e.idEvento " +
            "LEFT JOIN SitioColeta sc ON sc.idSitioColeta = ee.idEntidade " +
            "LEFT JOIN IsoladoColeta ic ON ic.evento.idEvento = e.idEvento " +
            "LEFT JOIN Microorganismo m ON m = ic.microorganismo " +
            "WHERE e.tipoEvento.idTipoEvento = 10 AND e.isActive = true " +
            "AND e.neonato.idNeonato = :idNeonato " +
            "ORDER BY e.dataEvento ASC")
    List<ColetaIsoladoSemAntibiogramasResponse> findColetaIsoladoByIdNeonato(@Param("idNeonato") Long idNeonato);



    @Query(value = """            
            SELECT COALESCE(COUNT(e.id_evento), 0) AS nEventos
            FROM evento e
            WHERE e.id_tipo_evento = 9
            AND e.id_neonato = :idNeonato
            AND e.is_active = true
            """, nativeQuery = true)
    Long getCirugiasNeonato(@Param("idNeonato") Long idNeonato);


    @Query("""
                SELECT ic
                FROM IsoladoColeta ic
                JOIN ic.evento e1
                JOIN Evento e2 ON e1.neonato.idNeonato = e2.neonato.idNeonato
                               AND e1.tipoEvento.idTipoEvento = e2.tipoEvento.idTipoEvento
                               AND e1.idEvento != e2.idEvento
                               AND ABS(DATEDIFF(e1.dataEvento, e2.dataEvento)) <= 15
                JOIN EventoEntidade ee1 ON ee1.evento.idEvento = e1.idEvento
                JOIN EventoEntidade ee2 ON ee2.evento.idEvento = e2.idEvento
                WHERE e1.neonato.idNeonato = :idNeonato
                  AND e1.tipoEvento.idTipoEvento = 10
                  AND ee1.idEntidade = ee2.idEntidade
                  AND ic.desconsiderarColeta = false
                ORDER BY e1.dataEvento, ee1.idEntidade
            """)
    List<IsoladoColeta> findDuplicatedIsoladoColeta(@Param("idNeonato") Long idNeonato);


    @Query("SELECT e.neonato.idNeonato FROM Evento e WHERE e.idEvento = :idEvento")
    Long findIdNeonatoByIdEvento(@Param("idEvento") Long idEvento);


}
