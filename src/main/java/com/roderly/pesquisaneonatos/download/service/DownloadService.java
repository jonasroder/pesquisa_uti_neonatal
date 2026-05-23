package com.roderly.pesquisaneonatos.download.service;

import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.download.dto.request.DownloadExcelNeonatoRequest;
import com.roderly.pesquisaneonatos.download.dto.response.DownloadExcelNeonatoResponse;
import com.roderly.pesquisaneonatos.download.mapper.SolicitacaoDownloadMapper;
import com.roderly.pesquisaneonatos.download.model.SolicitacaoDownload;
import com.roderly.pesquisaneonatos.download.repository.SolicitacaoDownloadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DownloadService {

    @Value("${file.storage.location}")
    private String fileStorageLocation;

    private final SolicitacaoDownloadRepository solicitacaoDownloadRepository;


    public ApiResponseDTO save(DownloadExcelNeonatoRequest request) throws IOException {
        var mapped = SolicitacaoDownloadMapper.downloadExcelNeonatoRequestToSolicitacaoDownload(request);
        var saved = solicitacaoDownloadRepository.save(mapped);
        return ApiResponseDTO.successMessage("A sua solicitação foi colocada na fila, você póde acompanhar o status e fazer o download pelo menu correspondente!");
    }



    public List<DownloadExcelNeonatoResponse> list() {
        var neonatoList = solicitacaoDownloadRepository.findAll(Sort.by(Sort.Direction.DESC, "idSolicitacaoDownload"));
        return neonatoList.stream()
                .map(SolicitacaoDownloadMapper::downloadSolicitacaoDownloadToExcelNeonatoResponse)
                .toList();
    }



    public byte[] getFileData(Long id) throws IOException {
        Path path = Paths.get(fileStorageLocation, id + ".xlsx");
        if (!Files.exists(path)) {
            throw new IOException("Arquivo não encontrado: " + path);
        }
        return Files.readAllBytes(path);
    }

    public String getDescricao(Long id) {
        return solicitacaoDownloadRepository.findById(id)
                .map(SolicitacaoDownload::getDescricao)
                .orElse("download");
    }


}
