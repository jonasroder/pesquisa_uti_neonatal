package com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.model;

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
@Table(name = "classe_antimicrobiano")
public class ClasseAntimicrobiano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classe_antimicrobiano")
    private Long idClasseAntimicrobano;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    public ClasseAntimicrobiano(Long idClasseAntimicrobano) {
        this.idClasseAntimicrobano = idClasseAntimicrobano;
    }
}

