package com.roderly.pesquisaneonatos.neonato.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "neonato_sitio_malformacao")
public class NeonatoSitioMalformacao extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_neonato_sitio_malformacao")
    private Long idNeonatoSitioMalformacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_neonato", referencedColumnName = "id_neonato", nullable = true)
    private Neonato neonato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sitio_malformacao", referencedColumnName = "id_sitio_malformacao")
    private SitioMalformacao sitioMalformacao;

    public NeonatoSitioMalformacao(Long idNeonatoSitioMalformacao) {
        this.idNeonatoSitioMalformacao = idNeonatoSitioMalformacao;
    }
}
