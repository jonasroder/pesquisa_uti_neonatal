package com.roderly.pesquisaneonatos.prontuario.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roderly.pesquisaneonatos.prontuario.model.AntibiogramaIsolado;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public record ColetaIsoladoResponse(
        Long idEvento,
        Long idEventoEntidade,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataEvento,
        String observacao,
        Long idSitioColeta,
        String descricao,
        Long id_isolado_coleta,
        Long id_microorganismo,
        Long id_perfil_resistencia_microorganismo,
        Long id_mecanismo_resistencia_microorganismo,
        List<AntibiogramaIsolado> antibiogramas
) {
}