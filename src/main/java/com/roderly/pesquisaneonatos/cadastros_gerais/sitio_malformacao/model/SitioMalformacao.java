package com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model;

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
@Table(name = "sitio_malformacao")
public class SitioMalformacao extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sitio_malformacao")
    private Long idSitioMalformacao;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    public SitioMalformacao(Long idSitioMalformacao) {
        this.idSitioMalformacao = idSitioMalformacao;
    }
}
