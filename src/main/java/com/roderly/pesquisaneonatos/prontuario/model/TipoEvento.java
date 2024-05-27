package com.roderly.pesquisaneonatos.prontuario.model;

import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_evento")
public class TipoEvento extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_evento")
    private Long idTipoEvento;

    @Column(name = "codigo", nullable = false, unique = true)
    private Long codigo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "cor_associada")
    private String corAssociada;

    @OneToMany(mappedBy = "tipoEvento", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evento> eventos = new HashSet<>();


    public TipoEvento(Long idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

}
