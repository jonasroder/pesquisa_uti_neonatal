package com.roderly.pesquisaneonatos.dashboard.mapper;

import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.Utilitarios.StringUtils;
import com.roderly.pesquisaneonatos.dashboard.dto.response.*;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@RequiredArgsConstructor
public class DashboardMapper {

    public static UltimosNeonatosCadastradosResponse neonatoToUltimosNeonatosCadastradosResponse(Neonato neonato) {
        return new UltimosNeonatosCadastradosResponse(
                neonato.getIdNeonato(),
                neonato.getProntuario(),
                DateUtil.LocalDateToDateBR(neonato.getDataNascimento()),
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



}
