package com.roderly.pesquisaneonatos.prontuario.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record ColetaIsoladoRequest(
        Long idEvento,
        Long idEventoEntidade,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataEvento,
        String observacao,
        Long idSitioColeta,
        String descricao,
        Long idIsoladoColeta,
        Long idMicroorganismo,
        Long idPerfilResistenciaMicroorganismo,
        Long idMecanismoResistenciaMicroorganismo,
        List<AntibiogramaIsoladoRequest> antibiogramas) {
}