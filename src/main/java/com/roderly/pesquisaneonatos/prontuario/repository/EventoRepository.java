package com.roderly.pesquisaneonatos.prontuario.repository;

import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoSemAntibiogramasResponse;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByNeonatoIdNeonato(Long idNeonato);


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
            "ic.mecanismoResistenciaMicroorganismo.idMecanismoResistenciaMicroorganismo) " +
            "FROM Evento e " +
            "JOIN EventoEntidade ee ON ee.evento.idEvento = e.idEvento " +
            "LEFT JOIN SitioColeta sc ON sc.idSitioColeta = ee.idEntidade " +
            "LEFT JOIN IsoladoColeta ic ON ic.evento.idEvento = e.idEvento " +
            "WHERE e.tipoEvento.idTipoEvento = 10 AND e.isActive = true " +
            "AND e.neonato.idNeonato = :idNeonato")
    List<ColetaIsoladoSemAntibiogramasResponse> findColetaIsoladoByIdNeonato(@Param("idNeonato") Long idNeonato);


}
