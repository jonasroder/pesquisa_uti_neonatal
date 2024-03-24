package com.roderly.microbiomelabufu.formulario_hamilton.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
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
public class FormularioHamilton extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formulario_hamilton;

    @OneToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humor_ansioso", referencedColumnName = "id_intensidade")
    private EscalaIntensidade humorAnsioso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tensao", referencedColumnName = "id_intensidade")
    private EscalaIntensidade tensao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medos", referencedColumnName = "id_intensidade")
    private EscalaIntensidade medos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insonia", referencedColumnName = "id_intensidade")
    private EscalaIntensidade insonia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dificuldades_intelectuais", referencedColumnName = "id_intensidade")
    private EscalaIntensidade dificuldades_intelectuais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humor_deprimido", referencedColumnName = "id_intensidade")
    private EscalaIntensidade humor_deprimido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "somatizacoes_motoras", referencedColumnName = "id_intensidade")
    private EscalaIntensidade somatizacoes_motoras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "somatizacoes_sensoriais", referencedColumnName = "id_intensidade")
    private EscalaIntensidade somatizacoes_sensoriais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sintomas_cardiovasculares", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_cardiovasculares;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sintomas_respiratorios", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_respiratorios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sintomas_gastrointestinais", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_gastrointestinais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sintomas_geniturinarios", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_geniturinarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sintomas_neurovegetativos", referencedColumnName = "id_intensidade")
    private EscalaIntensidade sintomas_neurovegetativos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comportamento_entrevista", referencedColumnName = "id_intensidade")
    private EscalaIntensidade comportamento_entrevista;
}
