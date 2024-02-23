package com.roderly.microbiomelabufu.consulta.model;


import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "prescricao_medicamento")
@Entity(name = "PrescricaoMedicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescricaoMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prescricao_medicamento;
    private String dosagem;
    private String instrucoes;
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "id_medicamento", referencedColumnName = "id_medicamento")
    private Medicamento medicamento;

    @ManyToOne
    @JoinColumn(name = "id_frequencia", referencedColumnName = "id_frequencia")
    private Frequencia frequencia;

    @ManyToOne
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    private Periodo periodo;


}
