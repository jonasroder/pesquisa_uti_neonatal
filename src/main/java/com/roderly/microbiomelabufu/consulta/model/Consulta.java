package com.roderly.microbiomelabufu.consulta.model;

import com.roderly.microbiomelabufu.usuario.model.Usuario;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "consulta")
@Entity(name = "Consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    private Paciente paciente;

    @OneToMany(mappedBy = "consulta")
    private Set<ConsultaDiagnostico> consultaDiagnosticos;

    @OneToMany(mappedBy = "consulta")
    private Set<PacienteMedicamento> pacienteMedicamentos;

}
