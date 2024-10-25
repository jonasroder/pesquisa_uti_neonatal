package com.roderly.pesquisaneonatos.neonato.dto.request;

import java.time.LocalDate;
import java.util.List;

public record NeonatoRequest(
        Long idNeonato,
        String nomeMae,
        String prontuario,
        LocalDate dataNascimento,
        LocalDate dataInternacao,
        LocalDate dataDesfecho,
        Boolean obito,
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
        Long idSitioMalformacao,
        Long idCausaObito,
        Boolean riscoInfeccioso,
        Boolean sepseClinica,
        List<NeonatoAusenciaUTIRequest> ausenciaUTI
) {
}
