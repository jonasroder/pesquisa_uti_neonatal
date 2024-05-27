package com.roderly.pesquisaneonatos.prontuario.model;

import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evento_entidade")
public class EventoEntidade extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_entidade")
    private Long idEventoEntidade;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    private Evento evento;

    @Column(name = "tipo_entidade", nullable = false)
    private String tipoEntidade;

    @Column(name = "id_entidade", nullable = false)
    private Long idEntidade;

}
