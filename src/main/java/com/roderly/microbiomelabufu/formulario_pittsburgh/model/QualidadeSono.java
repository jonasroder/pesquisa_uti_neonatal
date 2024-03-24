package com.roderly.microbiomelabufu.formulario_pittsburgh.model;

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
@Table(name = "qualidade_sono")
@Entity(name = "QualidadeSono")
public class QualidadeSono extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_qualidade_sono ;
    private String descricao;
    private Boolean is_active;

    public QualidadeSono(Long id_qualidade_sono ) {
        this.id_qualidade_sono  = id_qualidade_sono ;
    }

}
