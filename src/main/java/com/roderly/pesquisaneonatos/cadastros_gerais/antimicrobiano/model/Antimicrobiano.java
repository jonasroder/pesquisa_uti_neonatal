package com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.model.ClasseAntimicrobiano;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "antimicrobiano")
public class Antimicrobiano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antimicrobiano")
    private Long idAntimicrobiano;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_classe_antimicrobiano", referencedColumnName = "id_classe_antimicrobiano")
    private ClasseAntimicrobiano classeAntimicrobiano;

    public Antimicrobiano(Long idAntimicrobiano) {
        this.idAntimicrobiano = idAntimicrobiano;
    }
}
