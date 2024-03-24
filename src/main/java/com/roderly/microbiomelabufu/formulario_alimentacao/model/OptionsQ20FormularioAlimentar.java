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
@Table(name = "options_q20_formulario_alimentar")
@Entity
public class OptionsQ20FormularioAlimentar extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_options_q20;
    private String descricao;
    private Boolean is_active;

    public OptionsQ20FormularioAlimentar(Long id_options_q20) {
        this.id_options_q20 = id_options_q20;
    }
}