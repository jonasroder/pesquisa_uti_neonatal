package com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.model.ClasseAntimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.via_administracao.model.ViaAdministracao;
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

    @ManyToOne
    @JoinColumn(name = "id_via_administracao", referencedColumnName = "id_via_administracao")
    private ViaAdministracao viaAdministracao;

    public Antimicrobiano(Long idAntimicrobiano) {
        this.idAntimicrobiano = idAntimicrobiano;
    }
}
