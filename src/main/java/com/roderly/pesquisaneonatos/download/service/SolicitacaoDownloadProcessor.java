package com.roderly.pesquisaneonatos.download.service;

import com.roderly.pesquisaneonatos.download.mapper.SolicitacaoDownloadMapper;
import com.roderly.pesquisaneonatos.download.model.SolicitacaoDownload;
import com.roderly.pesquisaneonatos.download.model.StatusSolicitacao;
import com.roderly.pesquisaneonatos.download.repository.SolicitacaoDownloadRepository;
import com.roderly.pesquisaneonatos.neonato.service.NeonatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SolicitacaoDownloadProcessor {

    private final SolicitacaoDownloadRepository solicitacaoDownloadRepository;
    private final NeonatoService neonatoService;

    @Value("${file.storage.location}")
    private String fileStorageLocation;


    @Scheduled(fixedRate = 60000) // Executa a cada 1 minuto
    public void processarSolicitacoesPendentes() {
        List<SolicitacaoDownload> pendentes = solicitacaoDownloadRepository.findByStatus(StatusSolicitacao.PENDENTE);

        for (SolicitacaoDownload solicitacao : pendentes) {
            try {
                // Atualiza o status para EM_ANDAMENTO e salva
                atualizarStatusSolicitacao(solicitacao, StatusSolicitacao.EM_ANDAMENTO);
                System.out.println("Solicitação de relatório iniciado id: " + solicitacao.getIdSolicitacaoDownload());

                var filtrosRequest = SolicitacaoDownloadMapper.convertJsonToFiltrosRequest(solicitacao.getFiltros());

                // Gerar arquivo Excel
                byte[] excelBytes = neonatoService.generateExcelReport(filtrosRequest);

                // Salvar arquivo em diretório
                String filePath = salvarArquivo(solicitacao.getIdSolicitacaoDownload(), excelBytes);

                // Atualiza o status para CONCLUIDO e salva
                atualizarStatusSolicitacao(solicitacao, StatusSolicitacao.CONCLUIDO);
                System.out.println("Solicitação de relatório finalizada id: " + solicitacao.getIdSolicitacaoDownload());

            } catch (Exception e) {
                // Em caso de falha, atualiza o status para FALHA e salva
                atualizarStatusSolicitacao(solicitacao, StatusSolicitacao.FALHA);
                System.out.println("Solicitação de relatório com erro id: " + solicitacao.getIdSolicitacaoDownload());
                e.printStackTrace();
            }
        }
    }


    private String salvarArquivo(Long idSolicitacaoDownload, byte[] excelBytes) throws IOException {
        String directory = fileStorageLocation; // Usa o caminho configurado no application.properties
        String fileName = idSolicitacaoDownload + ".xlsx";
        Path filePath = Paths.get(directory, fileName);

        // Garante que o diretório exista
        Files.createDirectories(filePath.getParent());

        // Escreve o arquivo no local configurado
        Files.write(filePath, excelBytes);

        return filePath.toString(); // Retorna o caminho completo do arquivo
    }


    private void atualizarStatusSolicitacao(SolicitacaoDownload solicitacao, StatusSolicitacao status) {
        solicitacao.setStatus(status);
        solicitacaoDownloadRepository.save(solicitacao);
    }
}
