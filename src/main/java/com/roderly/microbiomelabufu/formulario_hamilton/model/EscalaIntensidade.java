package com.roderly.microbiomelabufu.formulario_hamilton.model;

import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.formulario_hamilton.model.FormularioHamilton;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "escala_intensidade")
@Entity(name = "EscalaIntensidade")
public class EscalaIntensidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_intensidade;
    private String descricao;
    private Boolean is_active;

    public EscalaIntensidade(Long id_intensidade) {
        this.id_intensidade = id_intensidade;
    }
}
