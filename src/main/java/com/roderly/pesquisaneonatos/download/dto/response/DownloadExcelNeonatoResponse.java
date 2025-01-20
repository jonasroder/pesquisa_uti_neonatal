package com.roderly.pesquisaneonatos.download.dto.response;

public record DownloadExcelNeonatoResponse(
        Long idSolicitacaoDownload,
        String descricao,
        String usuario,
        String data,
        String status
) {
}
