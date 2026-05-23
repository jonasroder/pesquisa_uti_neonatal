package com.roderly.pesquisaneonatos.dashboard.dto.projections;

import java.time.LocalDate;

public interface PacienteSemEventoProjection {
    Long getIdNeonato();
    String getProntuario();
    LocalDate getDataInternacao();
    LocalDate getUltimoEvento();
    String getNomeCompleto();
}
