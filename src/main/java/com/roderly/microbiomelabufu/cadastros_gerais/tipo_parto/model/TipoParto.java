package com.roderly.microbiomelabufu.cadastros_gerais.tipo_parto.model;

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
@Table(name = "tipo_parto")
public class TipoParto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_parto")
    private Long idTipoParto;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "tipoParto")
    private Set<Neonato> neonatoSet;

    public TipoParto(Long idTipoParto) {
        this.idTipoParto = idTipoParto;
    }
}
