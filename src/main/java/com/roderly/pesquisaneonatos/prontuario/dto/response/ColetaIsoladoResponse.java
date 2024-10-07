package com.roderly.pesquisaneonatos.prontuario.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ColetaIsoladoResponse(
        Long idEvento,
        Long idEventoEntidade,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataEvento,
        String observacao,
        Long idSitioColeta,
        String descricao
) {
}