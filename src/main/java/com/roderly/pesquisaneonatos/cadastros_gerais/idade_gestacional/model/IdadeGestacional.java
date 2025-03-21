package com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.model;

import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
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
@Table(name = "idade_gestacional")
public class IdadeGestacional extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idade_gestacional")
    private Long idIdadeGestacional;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "idadeGestacional")
    private Set<Neonato> neonatoSet;

    public IdadeGestacional(Long idIdadeGestacional) {
        this.idIdadeGestacional = idIdadeGestacional;
    }
}
