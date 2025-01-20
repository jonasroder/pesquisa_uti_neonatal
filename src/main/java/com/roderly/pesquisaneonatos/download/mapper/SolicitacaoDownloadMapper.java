package com.roderly.pesquisaneonatos.download.mapper;

import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.download.dto.request.DownloadExcelNeonatoRequest;
import com.roderly.pesquisaneonatos.download.dto.response.DownloadExcelNeonatoResponse;
import com.roderly.pesquisaneonatos.download.model.SolicitacaoDownload;

public class SolicitacaoDownloadMapper {

    public static SolicitacaoDownload downloadExcelNeonatoRequestToSolicitacaoDownload(DownloadExcelNeonatoRequest request) {

        var solicitacaoDownload = new SolicitacaoDownload();
        solicitacaoDownload.setDescricao(request.descricao());
        solicitacaoDownload.setFiltros(request.filtros());

        return solicitacaoDownload;
    }


    public static DownloadExcelNeonatoResponse downloadSolicitacaoDownloadToExcelNeonatoResponse(SolicitacaoDownload solicitacaoDownload) {
        return new DownloadExcelNeonatoResponse(
                solicitacaoDownload.getIdSolicitacaoDownload(),
                solicitacaoDownload.getDescricao(),
                solicitacaoDownload.getCriadoPor().getNomeCompleto(),
                DateUtil.LocalDateToDateBR(solicitacaoDownload.getCriadoEm()),
                solicitacaoDownload.getStatus().toString()
        );
    }

}
