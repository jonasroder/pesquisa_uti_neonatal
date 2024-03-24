package com.roderly.microbiomelabufu.cadastros_gerais.tipo_arquivo.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipo_arquivo")
@Entity(name = "TipoArquivo")
public class TipoArquivo extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_arquivo;
    private String descricao;
    private Boolean is_active;

    public TipoArquivo(Long id_tipo_arquivo) {
        this.id_tipo_arquivo = id_tipo_arquivo;
    }

}
