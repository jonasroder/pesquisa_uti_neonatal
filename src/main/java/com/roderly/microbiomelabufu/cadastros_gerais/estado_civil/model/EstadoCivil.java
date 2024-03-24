package com.roderly.microbiomelabufu.cadastros_gerais.estado_civil.model;

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
@Table(name = "estado_civil")
@Entity(name = "EstadoCivil")
public class EstadoCivil extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estado_civil;
    private String descricao;
    private Boolean is_active;


    @OneToMany(mappedBy = "estadoCivil")
    private Set<Paciente> pacientes;

    public EstadoCivil(Long id_estado_civil) {
        this.id_estado_civil = id_estado_civil;
    }

}
