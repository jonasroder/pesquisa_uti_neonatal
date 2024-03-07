package com.roderly.microbiomelabufu.formulario_hamilton.model;

import com.roderly.microbiomelabufu.cadastros_gerais.escala_intensidade.model.EscalaIntensidade;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formulario_hamilton")
@Entity(name = "FormularioHamilton")
public class FormularioHamilton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formulario_hamilton;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "humor_ansioso", referencedColumnName = "id_intensidade")
    private EscalaIntensidade humorAnsioso;

    @ManyToOne
    @JoinColumn(name = "tensao", referencedColumnName = "id_intensidade")
    private EscalaIntensidade tensao;

    @ManyToOne
    @JoinColumn(name = "medos", referencedColumnName = "id_intensidade")
    private EscalaIntensidade medos;

    @ManyToOne
    @JoinColumn(name = "insonia", referencedColumnName = "id_intensidade")
    private EscalaIntensidade insonia;

    @ManyToOne
    @JoinColumn(name = "dificuldades_intelectuais", referencedColumnName = "id_intensidade")
    private EscalaIntensidade dificuldades_intelectuais;

    @ManyToOne
    @JoinColumn(name = "humor_deprimido", referencedColumnName = "id_intensidade")
    private EscalaIntensidade humor_deprimido;

    @ManyToOne
    @JoinColumn(name = "somatizacoes_motoras", referencedColumnName = "id_intensidade")
    private EscalaIntensidade somatizacoes_motoras;

    @ManyToOne
    @JoinColumn(name = "somatizacoes_sensoriais", referencedColumnName = "id_intensidade")
    private EscalaIntensidade somatizacoes_sensoriais;

    @ManyToOne
    @JoinColumn(name = "sintomas_cardiovasculares", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_cardiovasculares;

    @ManyToOne
    @JoinColumn(name = "sintomas_respiratorios", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_respiratorios;

    @ManyToOne
    @JoinColumn(name = "sintomas_gastrointestinais", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_gastrointestinais;

    @ManyToOne
    @JoinColumn(name = "sintomas_geniturinarios", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_geniturinarios;

    @ManyToOne
    @JoinColumn(name = "sintomas_neurovegetativos", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_neurovegetativos;

    @ManyToOne
    @JoinColumn(name = "comportamento_entrevista", referencedColumnName = "id_intensidade")
    private EscalaIntensidade comportamento_entrevista;
}
