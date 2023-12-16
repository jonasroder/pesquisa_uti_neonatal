package com.roderly.microbiomelabufu.arquivo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "arquivo")
@Entity(name = "Arquivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_arquivo;

    private String entidade;

    private Long id_entidade;

    private Long id_tipo_arquivo;

    private String nome_arquivo;

    private String caminho_arquivo;

    private Long tamanho;

    private String formato;

    private LocalDateTime data_upload;

    @Column(columnDefinition = "JSON")
    private String metadados;
}
