package com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model;

import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "frequencia")
@Entity(name = "Frequencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Frequencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_frequencia;
    private String descricao;
    private Boolean is_active;


    public Frequencia(Long id_frequencia) {
        this.id_frequencia = id_frequencia;
    }
}
