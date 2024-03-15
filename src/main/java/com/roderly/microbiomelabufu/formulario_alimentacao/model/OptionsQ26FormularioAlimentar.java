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
@Table(name = "options_q26_formulario_alimentar")
@Entity
public class OptionsQ26FormularioAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_options_q26;
    private String descricao;
    private Boolean is_active;

    public OptionsQ26FormularioAlimentar(Long id_options_q26) {
        this.id_options_q26 = id_options_q26;
    }
}