package com.roderly.microbiomelabufu.agenda.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agenda")
@Entity(name = "Agenda")
public class Agenda extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Boolean dia_inteiro;
    private String url;
    private String descricao;
    private Boolean is_active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status_agenda")
    private StatusAgenda status_agenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_evento_agenda")
    private TipoEventoAgenda tipo_evento_agenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}
