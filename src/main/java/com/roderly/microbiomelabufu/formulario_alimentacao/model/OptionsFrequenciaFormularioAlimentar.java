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
@Table(name = "options_frequencia_formulario_alimentar")
@Entity(name = "OptionsFrequenciaFormularioAlimentar")
public class OptionsFrequenciaFormularioAlimentar extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_options_frequencia_formulario_alimentar;
    private String descricao;
    private Boolean is_active;

    public OptionsFrequenciaFormularioAlimentar(Long id_options_frequencia_formulario_alimentar) {
        this.id_options_frequencia_formulario_alimentar = id_options_frequencia_formulario_alimentar;
    }
}