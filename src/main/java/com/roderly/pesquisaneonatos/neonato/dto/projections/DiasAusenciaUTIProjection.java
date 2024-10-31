package com.roderly.pesquisaneonatos.neonato.dto.projections;

import java.time.LocalDate;

public interface DiasAusenciaUTIProjection {
    LocalDate getDataSaida();
    LocalDate getDataRetorno();
}
