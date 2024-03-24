package com.roderly.microbiomelabufu.cadastros_gerais.profissao.model;

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
@Table(name = "profissao")
@Entity(name = "Profissao")
public class Profissao extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profissao;
    private String descricao;
    private Boolean is_active;

    @OneToMany(mappedBy = "profissao")
    private Set<Paciente> pacientes;


    public Profissao(Long id_profissao) {
        this.id_profissao = id_profissao;
    }

}
