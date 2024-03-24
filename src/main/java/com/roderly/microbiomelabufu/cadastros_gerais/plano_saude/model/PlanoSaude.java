package com.roderly.microbiomelabufu.cadastros_gerais.plano_saude.model;

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
@Table(name = "plano_saude")
@Entity(name = "Plano_saude")
public class PlanoSaude extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plano_saude;
    private String descricao;
    private boolean is_active;


    @OneToMany(mappedBy = "planoSaude")
    private Set<Paciente> pacientes;

    public PlanoSaude(Long id_plano_saude) {
        this.id_plano_saude = id_plano_saude;
    }
}
