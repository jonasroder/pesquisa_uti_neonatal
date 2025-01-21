package com.roderly.pesquisaneonatos.neonato.dto.response;

import java.time.LocalDate;
import java.util.List;

public record NeonatoResponse(
        Long idNeonato,
        String nomeMae,
        String prontuario,
        LocalDate dataNascimento,
        LocalDate dataInternacao,
        LocalDate dataDesfecho,
        Boolean obito,
        Boolean gemelar,
        Long apgar1,
        Long apgar5,
        Long pesoGramas,
        Long idSexo,
        Long idPesoNascimento,
        Long idMotivoInternacao,
        Long idLocalNascimento,
        Long idIdadeGestacional,
        Long idTipoParto,
        Long idRoturaMembrana,
        List<Long> idSitioMalformacao,
        Long idCausaObito,
        Boolean riscoInfeccioso,
        Boolean sepseClinica,
        List<NeonatoAusenciaUTIResponse> ausenciaUTI
) {
}
