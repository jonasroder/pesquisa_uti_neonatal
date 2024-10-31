package com.roderly.pesquisaneonatos.prontuario.dto.response;

import java.time.LocalDate;

public record EventoResponse(
        Long idEvento,
        Long idNeonato,
        LocalDate dataEvento,
        Long idTipoEvento,
        String tipoEvento,
        String corEvento,
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
