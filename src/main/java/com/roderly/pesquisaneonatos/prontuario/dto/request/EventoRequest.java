package com.roderly.pesquisaneonatos.prontuario.dto.request;

import java.time.LocalDate;

public record EventoRequest(
        Long idEvento,
        Long idNeonato,
        LocalDate dataEvento,
        Long idTipoEvento,
        Long idEventoEntidade,
        String tipoEntidade,
        Long idEntidade,
        Boolean diaInteiro,
        String observacao,
        Boolean is_active,
        Long idEventoViaAdministracao,
        Long idViaAdministracao
) {
}
