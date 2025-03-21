package com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.model;

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
@Table(name = "peso_nascimento")
public class PesoNascimento extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_peso_nascimento")
    private Long idPesoNascimento;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "pesoNascimento")
    private Set<Neonato> neonatoSet;

    public PesoNascimento(Long idPesoNascimento) {
        this.idPesoNascimento = idPesoNascimento;
    }
}
