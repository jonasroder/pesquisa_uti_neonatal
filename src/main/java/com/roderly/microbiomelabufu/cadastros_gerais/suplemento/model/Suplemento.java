package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model;


import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;
import com.roderly.microbiomelabufu.consulta.model.PacienteSuplemento;
import com.roderly.microbiomelabufu.consulta.model.PrescricaoSuplemento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "suplemento")
@Entity(name = "Suplemento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Suplemento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_suplemento;
    private String nome;
    private String descricao;
    private String dosagem;
    private String instrucoes_uso;
    private Boolean is_active;

    @OneToMany(mappedBy = "suplemento")
    private Set<PacienteSuplemento> pacienteSuplementos;

    @OneToMany(mappedBy = "suplemento")
    private Set<PrescricaoSuplemento> prescricaoSuplementos;


    public Suplemento(Long id_suplemento){
        this.id_suplemento = id_suplemento;
    }
}
