package com.roderly.pesquisaneonatos.prontuario.model;


import com.roderly.pesquisaneonatos.cadastros_gerais.via_administracao.model.ViaAdministracao;
import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evento_via_administracao")
public class EventoViaAdministracao extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_via_administracao")
    private Long idEventoViaAdministracao;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_via_administracao", referencedColumnName = "id_via_administracao", nullable = false)
    private ViaAdministracao viaAdministracao;

    public EventoViaAdministracao(Long idEventoViaAdministracao) {
        this.idEventoViaAdministracao = idEventoViaAdministracao;
    }
}