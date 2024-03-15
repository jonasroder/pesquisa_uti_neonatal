package com.roderly.microbiomelabufu.formulario_pittsburgh.model;

import com.roderly.microbiomelabufu.consulta.model.Consulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formulario_pittsburgh")
@Entity(name = "FormularioPittsburgh")
public class FormularioPittsburgh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formulario_pittsburgh;

    private LocalTime horario_deitar;

    private Integer tempo_adormecer;

    private LocalTime horario_levantar;

    private Integer horas_sono;

    @OneToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dificuldade_adormecer", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades dificuldade_adormecer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acordou_meio_noite", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades acordou_meio_noite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "levantou_banheiro", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades levantou_banheiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dificuldade_respirar", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades dificuldade_respirar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tossiu_roncou", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades tossiu_roncou;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentiu_frio", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades sentiu_frio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentiu_calor", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades sentiu_calor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sonhos_ruins", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades sonhos_ruins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teve_dor", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades teve_dor;

    private String outra_dificuldade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frequencia_outra_dificuldade", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades frequencia_outra_dificuldade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualidade_sono", referencedColumnName = "id_qualidade_sono")
    private QualidadeSono qualidadeSono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicamento_sono", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades medicamento_sono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dificuldade_ficar_acordado", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades dificuldade_ficar_acordado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problema_entusiasmo", referencedColumnName = "id_problema_entusiasmo")
    private ProblemaEntusiasmo problema_entusiasmo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tem_parceiro_quarto", referencedColumnName = "id_presenca_parceiro")
    private PresencaParceiro tem_parceiro_quarto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ronco_forte", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades ronco_forte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paradas_respiracao", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades paradas_respiracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contracoes_pernas", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades contracoes_pernas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desorientacao_sono", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades desorientacao_sono;

    private String outras_alteracoes_sono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frequencia_outras_alteracoes", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades frequencia_outras_alteracoes;

}