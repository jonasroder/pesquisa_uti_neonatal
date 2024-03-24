package com.roderly.microbiomelabufu.cadastros_gerais.etnia.model;

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
@Table(name = "etnia")
@Entity(name = "Etnia")
public class Etnia extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_etnia;
    private String descricao;
    private Boolean is_active;


    @OneToMany(mappedBy = "etnia")
    private Set<Paciente> pacientes;


    public Etnia(Long id_etnia) {
        this.id_etnia = id_etnia;
    }

}
