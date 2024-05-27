package com.roderly.pesquisaneonatos.prontuario.dto.response;

import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;

import java.util.Set;

public record ProntuarioResponse(
        NeonatoListResponse neonato,
        Set<EventoResponse> eventos
) {
}
