package com.roderly.pesquisaneonatos.download.dto.request;

import java.util.List;

public record FiltrosExcelRequest(
        String dataInicial,
        String dataFinal,
        List<Integer> eventos,
        List<Integer> isolados,
        List<Integer> medicamentos,
        List<Integer> motivoInternacao,
        List<Integer> causaObito,
        List<Integer> sitioCirurgia,
        List<Integer> sitioColeta,
        List<Integer> mecanismoResistencia,
        List<Integer> perfilResistencia,
        Integer desfecho
) {
}

