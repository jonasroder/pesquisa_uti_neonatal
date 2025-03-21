package com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.model;

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
@Table(name = "causa_obito")
public class CausaObito extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_causa_obito")
    private Long idCausaObito;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "causaObito")
    private Set<Neonato> neonatoSet;

    public CausaObito(Long idCausaObito) {
        this.idCausaObito = idCausaObito;
    }
}
