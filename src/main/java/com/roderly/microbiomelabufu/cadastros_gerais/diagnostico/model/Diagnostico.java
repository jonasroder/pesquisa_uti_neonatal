package com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import com.roderly.microbiomelabufu.consulta.model.ConsultaDiagnostico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Table(name = "diagnostico")
@Entity(name = "Diagnostico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_diagnostico;
    private String nome;
    private String descricao;
    private String codigo_cid;
    private Boolean is_active;

    @OneToMany(mappedBy = "diagnostico")
    private Set<ConsultaDiagnostico> consultaDiagnosticos;


    public Diagnostico(Long id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

}
