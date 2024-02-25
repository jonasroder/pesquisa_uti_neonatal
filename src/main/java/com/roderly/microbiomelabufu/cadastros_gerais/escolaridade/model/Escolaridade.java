package com.roderly.microbiomelabufu.cadastros_gerais.escolaridade.model;

import com.roderly.microbiomelabufu.consulta.model.ConsultaDiagnostico;
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
@Table(name = "escolaridade")
@Entity(name = "Escolaridade")
public class Escolaridade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_escolaridade;
    private String descricao;
    private Boolean is_active;

    @OneToMany(mappedBy = "escolaridade")
    private Set<Paciente> pacientes;

    public Escolaridade(Long id_escolaridade) {
        this.id_escolaridade = id_escolaridade;
    }

}
