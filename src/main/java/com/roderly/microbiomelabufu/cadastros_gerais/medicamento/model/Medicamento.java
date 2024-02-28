package com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model;

import com.roderly.microbiomelabufu.cadastros_gerais.fabricante_medicamento.model.FabricanteMedicamento;
import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;
import com.roderly.microbiomelabufu.consulta.model.PrescricaoMedicamento;
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
    private String instrucoes;
    private Boolean is_active;

    @OneToMany(mappedBy = "medicamento")
    private Set<PacienteMedicamento> pacienteMedicamentos;

    @OneToMany(mappedBy = "medicamento")
    private Set<PrescricaoMedicamento> prescricaoMedicamentos;

    @ManyToOne
    @JoinColumn(name = "id_fabricante_medicamento", referencedColumnName = "id_fabricante_medicamento")
    private FabricanteMedicamento fabricanteMedicamento;




    public Medicamento(Long id_medicamento) {
        this.id_medicamento = id_medicamento;
    }
}
