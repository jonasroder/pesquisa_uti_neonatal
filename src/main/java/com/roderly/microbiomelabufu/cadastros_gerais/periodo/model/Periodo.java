package com.roderly.microbiomelabufu.cadastros_gerais.periodo.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "periodo")
@Entity(name = "Periodo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Periodo extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_periodo;
    private String descricao;
    private Boolean is_active;

    public Periodo(Long id_periodo) {
        this.id_periodo = id_periodo;
    }
}
