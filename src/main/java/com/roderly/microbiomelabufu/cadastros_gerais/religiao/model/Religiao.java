package com.roderly.microbiomelabufu.cadastros_gerais.religiao.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "religiao")
@Entity(name = "Religiao")
public class Religiao extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_religiao;
    private String descricao;
    private Boolean is_active;

    @OneToMany(mappedBy = "religiao")
    private Set<Paciente> pacientes;

    public Religiao(Long id_religiao) {
        this.id_religiao = id_religiao;
    }

}
