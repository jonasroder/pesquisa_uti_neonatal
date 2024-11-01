package com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model;

import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sitio_malformacao")
public class SitioMalformacao {
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
