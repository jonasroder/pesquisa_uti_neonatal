package com.roderly.microbiomelabufu.neonato.dto.request;

import java.time.LocalDate;

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
        Long idSitioCirurgia,
        Long idCausaObito,
        Boolean riscoInfeccioso,
        Boolean sepseClinica
) {
}
