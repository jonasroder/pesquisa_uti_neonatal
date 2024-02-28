package com.roderly.microbiomelabufu.cadastros_gerais.fabricante_medicamento.model;

import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.consulta.model.PrescricaoMedicamento;
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
@Table(name = "fabricante_medicamento")
@Entity(name = "FabricanteMedicamento")
public class FabricanteMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fabricante_medicamento;
    private String descricao;
    private Boolean is_active;

    @OneToMany(mappedBy = "fabricanteMedicamento")
    private Set<Medicamento> medicamentos;

    public FabricanteMedicamento(Long id_fabricante_medicamento) {
        this.id_fabricante_medicamento = id_fabricante_medicamento;
    }
}
