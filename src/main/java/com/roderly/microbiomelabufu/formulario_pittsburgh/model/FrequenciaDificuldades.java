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
@Table(name = "frequencia_dificuldades")
@Entity(name = "FrequenciaDificuldades")
public class FrequenciaDificuldades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_frequencia_dificuldades;
    private String descricao;
    private Boolean is_active;

    public FrequenciaDificuldades(Long id_frequencia_dificuldades) {
        this.id_frequencia_dificuldades = id_frequencia_dificuldades;
    }

}
