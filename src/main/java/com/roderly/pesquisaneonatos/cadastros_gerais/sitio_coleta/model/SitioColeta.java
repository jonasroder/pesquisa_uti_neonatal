package com.roderly.pesquisaneonatos.cadastros_gerais.sitio_coleta.model;

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
@Table(name = "sitio_coleta")
public class SitioColeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sitio_coleta")
    private Long idSitioColeta;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    public SitioColeta(Long idSitioColeta) {
        this.idSitioColeta = idSitioColeta;
    }
}
