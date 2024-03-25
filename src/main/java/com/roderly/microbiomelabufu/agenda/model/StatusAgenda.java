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
@Table(name = "status_agenda")
@Entity(name = "StatusAgenda")
public class StatusAgenda extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatusAgenda;
    private String nome;
    private String descricao;
    private String corAssociada;
    private Boolean isActive;


    public StatusAgenda(Long idStatusAgenda) {
        this.idStatusAgenda = idStatusAgenda;
    }
}
