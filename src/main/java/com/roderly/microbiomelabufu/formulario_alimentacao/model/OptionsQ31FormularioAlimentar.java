package com.roderly.microbiomelabufu.formulario_alimentacao.model;

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
@Table(name = "options_q31_formulario_alimentar")
@Entity(name = "OptionsQ31FormularioAlimentar")
public class OptionsQ31FormularioAlimentar extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_options_q31;
    private String descricao;
    private Boolean is_active;

    public OptionsQ31FormularioAlimentar(Long id_options_q31) {
        this.id_options_q31 = id_options_q31;
    }
}