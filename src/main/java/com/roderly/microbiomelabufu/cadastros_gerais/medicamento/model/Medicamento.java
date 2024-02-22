package com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model;

import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "medicamento")
@Entity(name = "Medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_medicamento;
    private String nome;
    private String dosagem;
    private Long id_fabricante_medicamento;
    private String instrucoes;
    private Boolean is_active;

    @OneToMany(mappedBy = "medicamento")
    private Set<PacienteMedicamento> pacienteMedicamentos;;



    public Medicamento(Long id_medicamento) {
        this.id_medicamento = id_medicamento;
    }
}
