package com.roderly.pesquisaneonatos.cadastros_gerais.rotura_membrana.model;

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
@Table(name = "rotura_membrana")
public class RoturaMembrana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rotura_membrana")
    private Long idRoturaMembrana;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "roturaMembrana")
    private Set<Neonato> neonatoSet;

    public RoturaMembrana(Long idRoturaMembrana) {
        this.idRoturaMembrana = idRoturaMembrana;
    }
}
