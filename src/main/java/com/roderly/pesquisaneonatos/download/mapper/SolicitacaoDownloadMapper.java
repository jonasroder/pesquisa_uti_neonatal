package com.roderly.pesquisaneonatos.download.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.download.dto.request.DownloadExcelNeonatoRequest;
import com.roderly.pesquisaneonatos.download.dto.request.FiltrosExcelRequest;
import com.roderly.pesquisaneonatos.download.dto.response.DownloadExcelNeonatoResponse;
import com.roderly.pesquisaneonatos.download.model.SolicitacaoDownload;

public class SolicitacaoDownloadMapper {

    public static SolicitacaoDownload downloadExcelNeonatoRequestToSolicitacaoDownload(DownloadExcelNeonatoRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        String filtrosJson;

        try {
            filtrosJson = objectMapper.writeValueAsString(request.filtros());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar filtros para JSON", e);
        }

        var solicitacaoDownload = new SolicitacaoDownload();
        solicitacaoDownload.setDescricao(request.descricao());
        solicitacaoDownload.setFiltros(filtrosJson);

        return solicitacaoDownload;
    }


    public static DownloadExcelNeonatoResponse downloadSolicitacaoDownloadToExcelNeonatoResponse(SolicitacaoDownload solicitacaoDownload) {
        return new DownloadExcelNeonatoResponse(
                solicitacaoDownload.getIdSolicitacaoDownload(),
                solicitacaoDownload.getDescricao(),
                solicitacaoDownload.getCriadoPor().getNomeCompleto(),
                DateUtil.LocalDateTimeToDateTimeBR(solicitacaoDownload.getCriadoEm()),
                solicitacaoDownload.getStatus().toString()
        );
    }


    public static FiltrosExcelRequest convertJsonToFiltrosRequest(String filtrosJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(filtrosJson, FiltrosExcelRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar filtros do JSON", e);
        }
    }

}
