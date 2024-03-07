package com.roderly.microbiomelabufu.consulta.model;

import com.roderly.microbiomelabufu.formulario_hamilton.model.FormularioHamilton;
import com.roderly.microbiomelabufu.usuario.model.Usuario;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consulta")
@Entity(name = "Consulta")
@FilterDef(name = "isActiveFilter", parameters = @ParamDef(name = "is_active", type = Boolean.class))
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
    @Filter(name = "isActiveFilter", condition = "is_active = :is_active")
    private Set<ConsultaDiagnostico> consultaDiagnosticos;

    @OneToMany(mappedBy = "consulta")
    @Filter(name = "isActiveFilter", condition = "is_active = :is_active")
    private Set<PacienteMedicamento> pacienteMedicamentos;

    @OneToMany(mappedBy = "consulta")
    @Filter(name = "isActiveFilter", condition = "is_active = :is_active")
    private Set<PacienteSuplemento> pacienteSuplementos;

    @OneToMany(mappedBy = "consulta")
    @Filter(name = "isActiveFilter", condition = "is_active = :is_active")
    private Set<ConsultaInformacaoSaude> consultaInformacoesSaude;

    @OneToMany(mappedBy = "consulta")
    @Filter(name = "isActiveFilter", condition = "is_active = :is_active")
    private Set<PrescricaoMedicamento> prescricaoMedicamentos;

    @OneToMany(mappedBy = "consulta")
    @Filter(name = "isActiveFilter", condition = "is_active = :is_active")
    private Set<PrescricaoSuplemento> prescricaoSuplementos;

    @OneToMany(mappedBy = "consulta")
    @Filter(name = "isActiveFilter", condition = "is_active = :is_active")
    private Set<ArquivoConsulta> arquivosConsultas;

    @OneToOne(mappedBy = "consulta")
    private FormularioHamilton formularioHamilton;


    public Consulta(Long id_consulta) {
        this.id_consulta = id_consulta;
    }

}
