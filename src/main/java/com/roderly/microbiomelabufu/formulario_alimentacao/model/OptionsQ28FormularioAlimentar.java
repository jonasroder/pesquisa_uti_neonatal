package com.roderly.microbiomelabufu.formulario_alimentacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "options_q28_formulario_alimentar")
@Entity
public class OptionsQ28FormularioAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_options_q28;
    private String descricao;
    private Boolean is_active;

    public OptionsQ28FormularioAlimentar(Long id_options_q28) {
        this.id_options_q28 = id_options_q28;
    }
}