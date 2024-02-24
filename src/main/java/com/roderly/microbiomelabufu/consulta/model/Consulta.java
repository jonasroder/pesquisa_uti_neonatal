package com.roderly.microbiomelabufu.consulta.model;

import com.roderly.microbiomelabufu.usuario.model.Usuario;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consulta")
@Entity(name = "Consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consulta;
    private Long id_tipo_consulta;
    private LocalDateTime data_hora;
    private String observacoes;
    private String sintomas;
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    private Paciente paciente;

    @OneToMany(mappedBy = "consulta")
    private Set<ConsultaDiagnostico> consultaDiagnosticos;

    @OneToMany(mappedBy = "consulta")
    private Set<PacienteMedicamento> pacienteMedicamentos;

    @OneToMany(mappedBy = "consulta")
    private Set<PacienteSuplemento> pacienteSuplementos;

    @OneToMany(mappedBy = "consulta")
    private Set<ConsultaInformacaoSaude> consultaInformacoesSaude;

    @OneToMany(mappedBy = "consulta")
    private Set<PrescricaoMedicamento> prescricaoMedicamentos;

    @OneToMany(mappedBy = "consulta")
    private Set<PrescricaoSuplemento> prescricaoSuplementos;

}
