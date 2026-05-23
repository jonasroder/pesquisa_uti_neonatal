package com.roderly.pesquisaneonatos.dashboard.dto.projections;

import java.time.LocalDate;

public interface InternacoesEmAbertoProjection {
    Long getIdNeonato();
    String getProntuario();
    LocalDate getDataNascimento();
    LocalDate getDataInternacao();
    String getNomeCompleto();
}
