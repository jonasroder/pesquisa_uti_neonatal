package com.roderly.pesquisaneonatos.dashboard.dto.response;

public record DashboardMetricasResponse(
        Double densidadeInfeccao180d,
        Long pacientesDia180d,
        Long totalObitos30d,
        Long totalDesfechos30d,
        Double taxaMortalidade30d,
        Double tempoMedioInternacao90d,
        Double atrasoMedioCadastro
) {
}
