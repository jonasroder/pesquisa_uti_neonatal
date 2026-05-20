package com.roderly.pesquisaneonatos.neonato.repository;

import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.neonato.specification.NeonatoSpecification;
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

    List<Neonato> findByIsActiveTrueOrderByDataInternacaoDesc();


    @Query(value = "SELECT COUNT(*) FROM neonato WHERE data_desfecho IS NULL AND is_active = 1", nativeQuery = true)
    Long countInternacoesEmAberto();


    @Query(value = """
            SELECT n.id_neonato, n.prontuario, n.data_nascimento, n.data_internacao, u.nome_completo
            FROM neonato n
            JOIN usuario u ON u.id_usuario = n.criado_por_id
            WHERE n.data_desfecho IS NULL AND n.is_active = 1
            ORDER BY n.data_internacao ASC
            """, nativeQuery = true)
    List<Object[]> findInternacoesEmAbertoList();


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


    default List<Neonato> findInternadosNosUltimos7Dias() {
        return findAll(NeonatoSpecification.internadoNosUltimosDias(7));
    }


    @Query(value = """
                WITH RECURSIVE ultimos_dias AS (
                    SELECT CURDATE() - INTERVAL 29 DAY AS dia
                    UNION ALL
                    SELECT dia + INTERVAL 1 DAY FROM ultimos_dias
                    WHERE dia + INTERVAL 1 DAY <= CURDATE()
                )
                SELECT
                    d.dia AS data,
                    COUNT(n.id_neonato) AS total
                FROM 
                    ultimos_dias d
                LEFT JOIN 
                    neonato n ON DATE(n.data_internacao) = d.dia
                GROUP BY 
                    d.dia
                ORDER BY 
                    d.dia
            """, nativeQuery = true)
    List<Object[]> buscarTotalInternacoesUltimos30Dias();


    @Query(value = """
                SELECT
                    DATE(e.data_evento) AS data,
                    m.acronimo AS agente,
                    COUNT(*) AS total,
                    GROUP_CONCAT(DISTINCT n.prontuario ORDER BY n.prontuario SEPARATOR ', ') AS prontuarios
                FROM isolado_coleta ic
                JOIN evento e ON e.id_evento = ic.id_evento
                JOIN microorganismo m ON m.id_microorganismo = ic.id_microorganismo
                JOIN neonato n ON n.id_neonato = e.id_neonato
                JOIN evento_entidade ee ON ee.id_evento = e.id_evento
                WHERE
                    e.data_evento >= CURDATE() - INTERVAL 180 DAY
                    AND e.is_active = TRUE
                    AND ic.desconsiderar_coleta = FALSE
                    AND ee.tipo_entidade = 'sitio_coleta'
                    AND ee.id_entidade NOT IN (4, 5, 7, 9)
                GROUP BY DATE(e.data_evento), m.acronimo
                ORDER BY DATE(e.data_evento), m.acronimo
            """, nativeQuery = true)
    List<Object[]> buscarInfeccoesPorAgenteUltimos180Dias();


    @Query(value = """
                SELECT
                    DATE(e.data_evento) AS data,
                    m.acronimo AS agente,
                    COUNT(*) AS total,
                    GROUP_CONCAT(DISTINCT n.prontuario ORDER BY n.prontuario SEPARATOR ', ') AS prontuarios
                FROM isolado_coleta ic
                JOIN evento e ON e.id_evento = ic.id_evento
                JOIN microorganismo m ON m.id_microorganismo = ic.id_microorganismo
                JOIN neonato n ON n.id_neonato = e.id_neonato
                JOIN evento_entidade ee ON ee.id_evento = e.id_evento
                WHERE
                    e.data_evento >= CURDATE() - INTERVAL 180 DAY
                    AND e.is_active = TRUE
                    AND ic.desconsiderar_coleta = FALSE
                    AND ee.tipo_entidade = 'sitio_coleta'
                    AND ee.id_entidade IN (4, 5, 7, 9)
                GROUP BY DATE(e.data_evento), m.acronimo
                ORDER BY DATE(e.data_evento), m.acronimo
            """, nativeQuery = true)
    List<Object[]> buscarColonizacoesPorAgenteUltimos180Dias();


    @Query(value = """
            SELECT
                pr.descricao AS perfil,
                COUNT(*) AS total
            FROM isolado_coleta ic
            JOIN perfil_resistencia_microorganismo pr ON pr.id_perfil_resistencia_microorganismo = ic.id_perfil_resistencia_microorganismo
            JOIN evento e ON e.id_evento = ic.id_evento
            WHERE e.data_evento >= CURDATE() - INTERVAL 180 DAY
                AND e.is_active = true AND ic.desconsiderar_coleta = false
            GROUP BY pr.descricao
            ORDER BY total DESC
            """, nativeQuery = true)
    List<Object[]> buscarDistribuicaoPerfisResistencia180Dias();


    @Query(value = """
                SELECT mr.descricao AS mecanismo, COUNT(*) AS total
                FROM isolado_coleta ic
                JOIN mecanismo_resistencia_microorganismo mr ON mr.id_mecanismo_resistencia_microorganismo = ic.id_mecanismo_resistencia_microorganismo
                JOIN evento e ON e.id_evento = ic.id_evento
                WHERE e.data_evento >= CURDATE() - INTERVAL 180 DAY
                      AND e.is_active = true AND ic.desconsiderar_coleta = false
                GROUP BY mr.descricao
                ORDER BY total DESC
            """, nativeQuery = true)
    List<Object[]> buscarDistribuicaoMecanismos180Dias();


    @Query(value = """
                SELECT a.descricao AS antimicrobiano, rm.descricao AS perfil_resistencia, COUNT(*) AS total
                FROM antibiograma_isolado ai
                JOIN antimicrobiano a ON a.id_antimicrobiano = ai.id_antimicrobiano
                JOIN resistencia_microorganismo rm ON rm.id_resistencia_microorganismo = ai.id_resistencia_microorganismo
                JOIN isolado_coleta ic on ic.id_isolado_coleta = ai.id_isolado_coleta
                JOIN evento e on e.id_evento = ic.id_evento
                JOIN neonato n on n.id_neonato = e.id_neonato
                WHERE e.data_evento >= CURDATE() - INTERVAL 180 DAY
                     AND e.is_active = true AND ic.desconsiderar_coleta = false AND n.is_active = true
                GROUP BY a.descricao, rm.descricao
                ORDER BY a.descricao, rm.descricao;
            """, nativeQuery = true)
    List<Object[]> buscarResistenciaPorAntimicrobiano180Dias();


    @Query(value = """
            SELECT COALESCE(SUM(DATEDIFF(IFNULL(data_desfecho, CURDATE()), data_internacao)), 0)
            FROM neonato
            WHERE is_active = true
              AND data_internacao <= CURDATE()
              AND data_internacao >= CURDATE() - INTERVAL 180 DAY
            """, nativeQuery = true)
    Long getTotalPacientesDia180d();


    @Query(value = """
            SELECT COUNT(*)
            FROM isolado_coleta ic
            JOIN evento e ON e.id_evento = ic.id_evento
            JOIN evento_entidade ee ON ee.id_evento = e.id_evento
            WHERE e.data_evento >= CURDATE() - INTERVAL 180 DAY
              AND e.is_active = TRUE
              AND ic.desconsiderar_coleta = FALSE
              AND ee.tipo_entidade = 'sitio_coleta'
              AND ee.id_entidade NOT IN (4, 5, 7, 9)
            """, nativeQuery = true)
    Long countInfeccoesUltimos180d();


    @Query(value = """
            SELECT
                SUM(CASE WHEN obito = 1 THEN 1 ELSE 0 END) AS total_obitos,
                COUNT(*) AS total_desfechos
            FROM neonato
            WHERE is_active = true
              AND data_desfecho IS NOT NULL
              AND data_desfecho >= CURDATE() - INTERVAL 30 DAY
            """, nativeQuery = true)
    List<Object[]> getObitoStats30d();


    @Query(value = """
            SELECT AVG(DATEDIFF(data_desfecho, data_internacao))
            FROM neonato
            WHERE is_active = true
              AND data_desfecho IS NOT NULL
              AND data_desfecho >= CURDATE() - INTERVAL 90 DAY
            """, nativeQuery = true)
    Double getTempoMedioInternacao90d();


    @Query(value = """
            SELECT AVG(DATEDIFF(DATE(criado_em), data_internacao))
            FROM neonato
            WHERE is_active = true
              AND data_internacao >= CURDATE() - INTERVAL 90 DAY
            """, nativeQuery = true)
    Double getAtrasoMedioCadastro();


    @Query(value = """
            SELECT m.acronimo AS descricao, COUNT(*) AS total
            FROM isolado_coleta ic
            JOIN evento e ON e.id_evento = ic.id_evento
            JOIN microorganismo m ON m.id_microorganismo = ic.id_microorganismo
            WHERE e.data_evento >= CURDATE() - INTERVAL 180 DAY
              AND e.is_active = true
              AND ic.desconsiderar_coleta = false
            GROUP BY m.acronimo
            ORDER BY total DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Object[]> buscarRankingMicroorganismos();


    @Query(value = """
            SELECT COALESCE(pn.descricao, 'Não informado') AS descricao, COUNT(*) AS total
            FROM neonato n
            LEFT JOIN peso_nascimento pn ON pn.id_peso_nascimento = n.id_peso_nascimento
            WHERE n.is_active = true
              AND n.data_internacao >= CURDATE() - INTERVAL 180 DAY
            GROUP BY pn.descricao
            ORDER BY total DESC
            """, nativeQuery = true)
    List<Object[]> buscarDistribuicaoPesoNascimento();


    @Query(value = """
            SELECT
                n.id_neonato,
                n.prontuario,
                n.data_internacao,
                MAX(e.data_evento) AS ultimo_evento,
                u.nome_completo
            FROM neonato n
            JOIN usuario u ON u.id_usuario = n.criado_por_id
            LEFT JOIN evento e ON e.id_neonato = n.id_neonato AND e.is_active = true
            WHERE n.data_desfecho IS NULL
              AND n.is_active = true
            GROUP BY n.id_neonato, n.prontuario, n.data_internacao, u.nome_completo
            HAVING MAX(e.data_evento) IS NULL OR MAX(e.data_evento) < CURDATE() - INTERVAL 7 DAY
            ORDER BY MAX(e.data_evento) ASC, n.data_internacao ASC
            """, nativeQuery = true)
    List<Object[]> buscarPacientesSemEventosRecentes();
}

