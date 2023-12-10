package com.roderly.microbiomelabufu.arquivo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "arquivo", indexes = {
        @Index(name = "idx_entidade_id_entidade", columnList = "entidade, id_entidade")
})
@Entity(name = "Arquivo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArquivoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArquivo;

    @Column(name = "entidade", nullable = false)
    private String entidade;

    @Column(name = "id_entidade", nullable = false)
    private Integer idEntidade;

    @Column(name = "id_tipo_arquivo")
    private Integer idTipoArquivo;

    @Column(name = "nome_arquivo", nullable = false)
    private String nomeArquivo;

    @Column(name = "caminho_arquivo", nullable = false)
    private String caminhoArquivo;

    @Column(name = "tamanho")
    private Long tamanho;

    @Column(name = "formato")
    private String formato;

    @Column(name = "data_upload")
    private LocalDateTime dataUpload;

    @Column(name = "metadados", columnDefinition = "JSON")
    private String metadados;

    @Column(name = "is_active")
    private Boolean isActive;
}
