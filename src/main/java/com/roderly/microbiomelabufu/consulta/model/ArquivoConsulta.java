package com.roderly.microbiomelabufu.consulta.model;

import com.roderly.microbiomelabufu.cadastros_gerais.tipo_arquivo.model.TipoArquivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arquivo_consulta")
@Entity(name = "ArquivoConsulta")
public class ArquivoConsulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_arquivo;
    private String nome_arquivo;
    private String caminho_arquivo;
    private Long tamanho;
    private String formato;
    private LocalDateTime data_upload;
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "id_tipo_arquivo", referencedColumnName = "id_tipo_arquivo")
    private TipoArquivo tipoArquivo;

}
