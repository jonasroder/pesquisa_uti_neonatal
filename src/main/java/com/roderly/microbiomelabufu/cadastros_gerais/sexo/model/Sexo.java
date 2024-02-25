package com.roderly.microbiomelabufu.cadastros_gerais.sexo.model;

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
@Table(name = "sexo")
@Entity(name = "Sexo")
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sexo;
    private String descricao;
    private Boolean is_active;

    @OneToMany(mappedBy = "sexo")
    private Set<Paciente> pacientes;

    public Sexo(Long id_sexo) {
        this.id_sexo = id_sexo;
    }

}
