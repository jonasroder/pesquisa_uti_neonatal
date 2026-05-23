package com.roderly.pesquisaneonatos.neonato.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NeonatoRequest(
        Long idNeonato,
        String nomeMae,
        @NotBlank String prontuario,
        @NotNull LocalDate dataNascimento,
        @NotNull LocalDate dataInternacao,
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
        List<NeonatoAusenciaUTIRequest> ausenciaUTI
) {
}
