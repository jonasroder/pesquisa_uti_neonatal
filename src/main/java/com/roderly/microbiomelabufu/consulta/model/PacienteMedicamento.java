package com.roderly.microbiomelabufu.consulta.model;


import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.login.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "paciente_medicamento")
@Entity(name = "PacienteMedicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paciente_medicamento;
    private String dosagem;
    private Long id_frequencia;
    private Long id_periodo;
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "id_medicamento", referencedColumnName = "id_medicamento")
    private Medicamento medicamento;


}
