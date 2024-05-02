package com.roderly.microbiomelabufu.cadastros_gerais.local_nascimento.model;

import com.roderly.microbiomelabufu.neonato.model.Neonato;
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
@Table(name = "local_nascimento")
public class LocalNascimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local_nascimento ")
    private Long idLocalNascimento;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "localNascimento")
    private Set<Neonato> neonatoSet;

    public LocalNascimento(Long idLocalNascimento) {
        this.idLocalNascimento = idLocalNascimento;
    }
}
