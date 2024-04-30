package com.roderly.microbiomelabufu.cadastros_gerais.sexo.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import com.roderly.microbiomelabufu.paciente.model.Neonato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sexo")
public class Sexo extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sexo")
    private Long idSexo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "sexo")
    private Set<Neonato> pacientes;

    public Sexo(Long idSexo) {
        this.idSexo = idSexo;
    }
}
