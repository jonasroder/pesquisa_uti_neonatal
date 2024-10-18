package com.roderly.pesquisaneonatos.cadastros_gerais.tipo_antimicrobiano.model;

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
@Table(name = "tipo_antimicrobiano")
public class TipoAntimicrobiano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_antimicrobiano")
    private Long idTipoAntimicrobiano;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    public TipoAntimicrobiano(Long idTipoAntimicrobiano) {
        this.idTipoAntimicrobiano = idTipoAntimicrobiano;
    }
}