package com.roderly.pesquisaneonatos.infra.email;

import java.time.LocalDateTime;

public record UltimaColetaInfo(LocalDateTime criadoEm, String nomeResponsavel) {

    public boolean isAntesDe(LocalDateTime limite) {
        return criadoEm == null || criadoEm.isBefore(limite);
    }

    public static UltimaColetaInfo maisRecente(UltimaColetaInfo a, UltimaColetaInfo b) {
        if (a == null || a.criadoEm() == null) return b;
        if (b == null || b.criadoEm() == null) return a;
        return a.criadoEm().isAfter(b.criadoEm()) ? a : b;
    }
}
