package com.roderly.pesquisaneonatos.download.service;

import com.roderly.pesquisaneonatos.download.model.SolicitacaoDownload;
import com.roderly.pesquisaneonatos.download.model.StatusSolicitacao;
import com.roderly.pesquisaneonatos.download.repository.SolicitacaoDownloadRepository;
import com.roderly.pesquisaneonatos.neonato.service.NeonatoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SolicitacaoDownloadProcessor {

    @Autowired
    private SolicitacaoDownloadRepository solicitacaoDownloadRepository;

    @Autowired
    private NeonatoService neonatoService;

    @Transactional
    @Scheduled(fixedRate = 60000) // Executa a cada 1 minuto
    public void processarSolicitacoesPendentes() {
        List<SolicitacaoDownload> pendentes = solicitacaoDownloadRepository.findByStatus(StatusSolicitacao.PENDENTE);

        for (SolicitacaoDownload solicitacao : pendentes) {
            try {
                solicitacao.setStatus(StatusSolicitacao.EM_ANDAMENTO);
                solicitacaoDownloadRepository.save(solicitacao);

                // Gerar arquivo Excel
                byte[] excelBytes = neonatoService.generateExcelReport();

                // Salvar arquivo em diretório
                String filePath = salvarArquivo(solicitacao.getIdSolicitacaoDownload(), excelBytes);

                solicitacao.setStatus(StatusSolicitacao.CONCLUIDO);
                solicitacaoDownloadRepository.save(solicitacao);
            } catch (Exception e) {
                solicitacao.setStatus(StatusSolicitacao.FALHA);
                solicitacaoDownloadRepository.save(solicitacao);
                e.printStackTrace();
            }
        }

        System.out.println("Caminho absoluto usado: " + Paths.get(fileStorageLocation).toAbsolutePath());
    }


    @Value("${file.storage.location}")
    private String fileStorageLocation;

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


}
