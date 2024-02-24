package com.roderly.microbiomelabufu.consulta.model;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consulta_diagnostico")
@Entity(name = "ConsultaDiagonostico")

public class ConsultaDiagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consulta_diagnostico;
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "id_diagnostico", referencedColumnName = "id_diagnostico")
    private Diagnostico diagnostico;
}
