package com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_antimicrobiano.model.TipoAntimicrobiano;
import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
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
public class ClasseAntimicrobiano extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classe_antimicrobiano")
    private Long idClasseAntimicrobano;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_antimicrobiano", referencedColumnName = "id_tipo_antimicrobiano")
    private TipoAntimicrobiano tipoAntimicrobiano;

    public ClasseAntimicrobiano(Long idClasseAntimicrobano) {
        this.idClasseAntimicrobano = idClasseAntimicrobano;
    }

    public static ClasseAntimicrobiano of(Long id) {
        return id != null ? new ClasseAntimicrobiano(id) : null;
    }
}

