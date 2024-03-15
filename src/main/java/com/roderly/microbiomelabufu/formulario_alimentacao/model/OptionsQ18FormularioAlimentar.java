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
@Table(name = "options_q18_formulario_alimentar")
@Entity(name = "OptionsQ18FormularioAlimentar")
public class OptionsQ18FormularioAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_options_q18;
    private String descricao;
    private Boolean is_active;

    public OptionsQ18FormularioAlimentar(Long id_options_q18) {
        this.id_options_q18 = id_options_q18;
    }
}