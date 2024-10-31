package com.roderly.pesquisaneonatos.prontuario.model;

import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evento")
public class Evento extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_evento", referencedColumnName = "id_tipo_evento")
    private TipoEvento tipoEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_neonato", referencedColumnName = "id_neonato")
    private Neonato neonato;

    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fim")
    private LocalTime horaFim;

    @Column(name = "dia_inteiro")
    private Boolean diaInteiro = false;

    @Column(name = "observacao")
    private String observacao;

    @OneToOne(mappedBy = "evento")
    private EventoEntidade eventoEntidade;

    @OneToOne(mappedBy = "evento")
    private IsoladoColeta isoladoColeta;

    @OneToOne(mappedBy = "evento")
    private EventoViaAdministracao eventoViaAdministracao;


    public Evento(Long idEvento) {
        this.idEvento = idEvento;
    }
}
