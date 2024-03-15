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
@Table(name = "options_q30_formulario_alimentar")
@Entity(name = "OptionsQ30FormularioAlimentar")
public class OptionsQ30FormularioAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_options_q30;
    private String descricao;
    private Boolean is_active;

    public OptionsQ30FormularioAlimentar(Long id_options_q30) {
        this.id_options_q30 = id_options_q30;
    }
}