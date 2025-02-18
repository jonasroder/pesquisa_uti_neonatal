package com.roderly.pesquisaneonatos.download.dto.request;

import java.util.Map;

public record DownloadExcelNeonatoRequest(
        String descricao,
        Map<String, Object> filtros
) {
}
