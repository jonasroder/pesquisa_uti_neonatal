package com.roderly.pesquisaneonatos.download.model;


import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitacao_download")
public class SolicitacaoDownload extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao_download")
    private Long idSolicitacaoDownload;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Lob
    @Column(name = "filtros")
    private String filtros;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;


    public SolicitacaoDownload(Long idSolicitacaoDownload) {
        this.idSolicitacaoDownload = idSolicitacaoDownload;
    }
}