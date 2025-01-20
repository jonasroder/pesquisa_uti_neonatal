package com.roderly.pesquisaneonatos.download.repository;

import com.roderly.pesquisaneonatos.download.model.SolicitacaoDownload;
import com.roderly.pesquisaneonatos.download.model.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoDownloadRepository extends JpaRepository<SolicitacaoDownload, Long> {

    List<SolicitacaoDownload> findByStatus(StatusSolicitacao status);

}


