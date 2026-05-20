package com.roderly.pesquisaneonatos.dashboard.mapper;

import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.Utilitarios.StringUtils;
import com.roderly.pesquisaneonatos.dashboard.dto.response.*;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@RequiredArgsConstructor
public class DashboardMapper {

    public static UltimosNeonatosCadastradosResponse neonatoToUltimosNeonatosCadastradosResponse(Neonato neonato) {
        return new UltimosNeonatosCadastradosResponse(
                neonato.getIdNeonato(),
                neonato.getProntuario(),
                DateUtil.LocalDateToDateBR(neonato.getDataNascimento()),
                DateUtil.LocalDateToDateBR(neonato.getDataInternacao()),
                DateUtil.LocalDateToDateBR(neonato.getCriadoEm()),
                StringUtils.getPrimeiroEUltimoNome(neonato.getCriadoPor().getNomeCompleto())
        );
    }


    public static CadastrosPorDiaDTOResponse mapToCadastrosPorDiaDTO(Object[] row) {
        return new CadastrosPorDiaDTOResponse(
                DateUtil.LocalDateToDateBR(((java.sql.Date) row[0]).toLocalDate()),
                ((Number) row[1]).longValue()
        );
    }


    public static InfeccoesPorAgenteResponse mapToInfeccoesPorAgenteResponse(Object[] obj) {
        return new InfeccoesPorAgenteResponse(
                DateUtil.LocalDateToDateBR(((Date) obj[0]).toLocalDate()),
                (String) obj[1],
                ((Number) obj[2]).longValue(),
                (String) obj[3]
        );
    }

    public static PerfilResistenciaDistribuicaoResponse toPerfilResponse(Object[] obj) {
        return new PerfilResistenciaDistribuicaoResponse((String) obj[0], ((Number) obj[1]).longValue());
    }

    public static MecanismoResistenciaDistribuicaoResponse toMecanismoResponse(Object[] obj) {
        return new MecanismoResistenciaDistribuicaoResponse((String) obj[0], ((Number) obj[1]).longValue());
    }

    public static AntimicrobianoResistenciaResponse toAntimicrobianoResponse(Object[] obj) {
        return new AntimicrobianoResistenciaResponse((String) obj[0], (String) obj[1], ((Number) obj[2]).longValue());
    }


    public static PacienteSemEventoResponse toPacienteSemEventoResponse(Object[] row) {
        String ultimoEvento = null;
        if (row[3] != null) {
            if (row[3] instanceof Date d) {
                ultimoEvento = DateUtil.LocalDateToDateBR(d.toLocalDate());
            } else if (row[3] instanceof Timestamp ts) {
                ultimoEvento = DateUtil.LocalDateToDateBR(ts.toLocalDateTime());
            }
        }
        return new PacienteSemEventoResponse(
                ((Number) row[0]).longValue(),
                (String) row[1],
                DateUtil.LocalDateToDateBR(((Date) row[2]).toLocalDate()),
                ultimoEvento,
                StringUtils.getPrimeiroEUltimoNome((String) row[4])
        );
    }


    public static UltimosNeonatosCadastradosResponse mapInternacoesEmAberto(Object[] row) {
        return new UltimosNeonatosCadastradosResponse(
                ((Number) row[0]).longValue(),
                (String) row[1],
                DateUtil.LocalDateToDateBR(((java.sql.Date) row[2]).toLocalDate()),
                DateUtil.LocalDateToDateBR(((java.sql.Date) row[3]).toLocalDate()),
                null,
                StringUtils.getPrimeiroEUltimoNome((String) row[4])
        );
    }



}
