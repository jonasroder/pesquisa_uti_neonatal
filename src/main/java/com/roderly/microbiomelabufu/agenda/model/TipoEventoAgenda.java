package com.roderly.microbiomelabufu.agenda.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipo_evento_agenda")
@Entity(name = "TipoEventoAgenda")
public class TipoEventoAgenda  extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_evento_agenda;
    private String nome;
    private String descricao;
    private String cor_associada;
    private Boolean is_active;

    public TipoEventoAgenda(Long id_tipo_evento_agenda) {
        this.id_tipo_evento_agenda = id_tipo_evento_agenda;
    }
}
