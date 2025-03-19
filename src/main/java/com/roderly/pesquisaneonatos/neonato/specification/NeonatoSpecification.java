package com.roderly.pesquisaneonatos.neonato.specification;

import com.roderly.pesquisaneonatos.download.dto.request.FiltrosExcelRequest;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NeonatoSpecification {

    public static Specification<Neonato> byFiltros(FiltrosExcelRequest filtros, String tipo) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // ⬇️ Filtrar somente neonatos ativos
            predicates.add(builder.isTrue(root.get("isActive")));

            // Filtrar por intervalo de datas de internacao (se preenchido)
            String dataInicial = filtros.dataInicial();
            String dataFinal = filtros.dataFinal();

            if (dataInicial != null && dataFinal != null) {
                LocalDate start = LocalDate.parse(dataInicial, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate end = LocalDate.parse(dataFinal, DateTimeFormatter.ISO_LOCAL_DATE);
                predicates.add(builder.between(root.get("dataInternacao"), start, end));

            } else if (dataInicial != null) {
                LocalDate start = LocalDate.parse(dataInicial, DateTimeFormatter.ISO_LOCAL_DATE);
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataInternacao"), start));

            } else if (dataFinal != null) {
                LocalDate end = LocalDate.parse(dataFinal, DateTimeFormatter.ISO_LOCAL_DATE);
                predicates.add(builder.lessThanOrEqualTo(root.get("dataInternacao"), end));
            }



            // Filtrar por eventos (se preenchido)
            if (filtros.eventos() != null && !filtros.eventos().isEmpty()) {
                query.distinct(true);

                Join<Neonato, Evento> eventoJoin = root.join("eventoList", JoinType.LEFT);
                predicates.add(eventoJoin.get("tipoEvento").get("idTipoEvento").in(filtros.eventos()));
            }



            // Filtrar por isolados (id_microorganismo) se a lista estiver preenchida
            if (filtros.isolados() != null && !filtros.isolados().isEmpty()) {
                query.distinct(true);

                Join<Neonato, Evento> eventoJoinForIsolados = root.join("eventoList", JoinType.LEFT);
                Join<Evento, ?> isoladoJoin = eventoJoinForIsolados.join("isoladoColeta", JoinType.LEFT);
                predicates.add(isoladoJoin.get("microorganismo").get("idMicroorganismo").in(filtros.isolados()));
            }



            // Filtrar por medicamentos (id_entidade em 'evento_entidade')
            if (filtros.medicamentos() != null && !filtros.medicamentos().isEmpty()) {
                query.distinct(true);
                // Join: Neonato -> Evento -> EventoEntidade
                Join<Neonato, Evento> eventoJoinForMedicamentos = root.join("eventoList", JoinType.LEFT);
                Join<Evento, ?> eventoEntidadeJoin = eventoJoinForMedicamentos.join("eventoEntidade", JoinType.LEFT);

                // tipo_entidade = 'antimicrobiano'
                predicates.add(builder.equal(eventoEntidadeJoin.get("tipoEntidade"), "antimicrobiano"));

                // id_entidade IN (lista de IDs de antimicrobianos)
                predicates.add(eventoEntidadeJoin.get("idEntidade").in(filtros.medicamentos()));
            }



            // Filtrar por sítio de cirurgia (id_entidade em 'evento_entidade')
            if (filtros.sitioCirurgia() != null && !filtros.sitioCirurgia().isEmpty()) {
                query.distinct(true);
                Join<Neonato, Evento> eventoJoinForSitioCirurgia = root.join("eventoList", JoinType.LEFT);
                Join<Evento, ?> eventoEntidadeJoin = eventoJoinForSitioCirurgia.join("eventoEntidade", JoinType.LEFT);

                // tipo_entidade = 'sitio_cirurgia'
                predicates.add(builder.equal(eventoEntidadeJoin.get("tipoEntidade"), "sitio_cirurgia"));
                // id_entidade IN (lista de IDs de sítio de cirurgia)
                predicates.add(eventoEntidadeJoin.get("idEntidade").in(filtros.sitioCirurgia()));
            }



            // Filtrar por sítio de coleta (id_entidade em 'evento_entidade')
            if (filtros.sitioColeta() != null && !filtros.sitioColeta().isEmpty()) {
                query.distinct(true);
                Join<Neonato, Evento> eventoJoinForSitioColeta = root.join("eventoList", JoinType.LEFT);
                Join<Evento, ?> eventoEntidadeJoin = eventoJoinForSitioColeta.join("eventoEntidade", JoinType.LEFT);

                // tipo_entidade = 'sitio_coleta'
                predicates.add(builder.equal(eventoEntidadeJoin.get("tipoEntidade"), "sitio_coleta"));
                // id_entidade IN (lista de IDs de sítio de coleta)
                predicates.add(eventoEntidadeJoin.get("idEntidade").in(filtros.sitioColeta()));
            }



            // Filtrar por motivo de internação (id_motivo_internacao)
            if (filtros.motivoInternacao() != null && !filtros.motivoInternacao().isEmpty()) {
                query.distinct(true);
                predicates.add(
                        root.get("motivoInternacao").get("idMotivoInternacao").in(filtros.motivoInternacao())
                );
            }



            // Filtrar por causa de óbito (id_causa_obito)
            if (filtros.causaObito() != null && !filtros.causaObito().isEmpty()) {
                query.distinct(true);
                predicates.add(
                        root.get("causaObito").get("idCausaObito").in(filtros.causaObito())
                );
            }



            // Filtrar por mecanismoResistencia (isoladoColeta.mecanismoResistenciaMicroorganismo)
            if (filtros.mecanismoResistencia() != null && !filtros.mecanismoResistencia().isEmpty()) {
                query.distinct(true);
                // Join: Neonato -> Evento -> IsoladoColeta
                Join<Neonato, Evento> eventoJoinForMecRes = root.join("eventoList", JoinType.LEFT);
                Join<Evento, ?> isoladoJoin = eventoJoinForMecRes.join("isoladoColeta", JoinType.LEFT);

                // id_mecanismo_resistencia_microorganismo in (lista)
                predicates.add(isoladoJoin.get("mecanismoResistenciaMicroorganismo")
                        .get("idMecanismoResistenciaMicroorganismo")
                        .in(filtros.mecanismoResistencia()));
            }



            // Filtrar por perfilResistencia (isoladoColeta.perfilResistenciaMicroorganismo)
            if (filtros.perfilResistencia() != null && !filtros.perfilResistencia().isEmpty()) {
                query.distinct(true);
                // Join: Neonato -> Evento -> IsoladoColeta
                Join<Neonato, Evento> eventoJoinForPerfilRes = root.join("eventoList", JoinType.LEFT);
                Join<Evento, ?> isoladoJoin = eventoJoinForPerfilRes.join("isoladoColeta", JoinType.LEFT);

                // id_perfil_resistencia_microorganismo in (lista)
                predicates.add(isoladoJoin.get("perfilResistenciaMicroorganismo")
                        .get("idPerfilResistenciaMicroorganismo")
                        .in(filtros.perfilResistencia()));
            }



            // Filtrar por desfecho (obito)
            if (filtros.desfecho() != null) {
                List<Boolean> obitoValues = new ArrayList<>();

                if (filtros.desfecho() == 1) {
                    obitoValues.add(true);
                } else if (filtros.desfecho() == 2) {
                    obitoValues.add(false);
                }

                if (!obitoValues.isEmpty()) {
                    query.distinct(true);
                    predicates.add(root.get("obito").in(obitoValues));
                }
            }



            // Lógica condicional para tipo "controle" ou "infectado"
            if ("controle".equalsIgnoreCase(tipo)) {
                // Excluir neonatos que tenham ao menos um evento com registro em isoladoColeta
                Subquery<Long> subquery = query.subquery(Long.class);
                var subRoot = subquery.from(Neonato.class);
                Join<Neonato, Evento> subEventoJoin = subRoot.join("eventoList", JoinType.LEFT);

                subquery.select(subRoot.get("idNeonato"))
                        .where(
                                builder.equal(subRoot.get("idNeonato"), root.get("idNeonato")),
                                builder.isNotNull(subEventoJoin.get("isoladoColeta"))
                        );

                predicates.add(builder.not(builder.exists(subquery)));

            } else if ("infectado".equalsIgnoreCase(tipo)) {
                // Incluir apenas neonatos que tenham pelo menos um evento com registro em isoladoColeta
                query.distinct(true);
                Join<Neonato, Evento> eventoJoin = root.join("eventoList", JoinType.LEFT);
                predicates.add(builder.isNotNull(eventoJoin.get("isoladoColeta")));
            }

            // ⬇️ ORDENAR PELO CAMPO dataInternacao EM ORDEM CRESCENTE
            query.orderBy(builder.asc(root.get("dataInternacao")));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
