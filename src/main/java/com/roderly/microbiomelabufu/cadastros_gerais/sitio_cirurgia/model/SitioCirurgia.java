package com.roderly.microbiomelabufu.cadastros_gerais.sitio_cirurgia.model;

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
@Table(name = "sitio_cirurgia")
public class SitioCirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sitio_cirurgia")
    private Long idSitioCirurgia;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "sitioCirurgia")
    private Set<Neonato> neonatoSet;

    public SitioCirurgia(Long idSitioCirurgia) {
        this.idSitioCirurgia = idSitioCirurgia;
    }
}
