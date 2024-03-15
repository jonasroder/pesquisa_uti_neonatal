package com.roderly.microbiomelabufu.formulario_pittsburgh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "presenca_parceiro")
@Entity(name = "PresencaParceiro")
public class PresencaParceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_presenca_parceiro ;
    private String descricao;
    private Boolean is_active;

    public PresencaParceiro(Long id_presenca_parceiro) {
        this.id_presenca_parceiro = id_presenca_parceiro;
    }


}
