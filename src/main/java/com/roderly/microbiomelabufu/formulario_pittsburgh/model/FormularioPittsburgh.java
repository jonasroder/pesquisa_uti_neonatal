package com.roderly.microbiomelabufu.formulario_pittsburgh.model;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia_dificuldades.model.FrequenciaDificuldades;
import com.roderly.microbiomelabufu.cadastros_gerais.presenca_parceiro.model.PresencaParceiro;
import com.roderly.microbiomelabufu.cadastros_gerais.problema_entusiasmo.model.ProblemaEntusiasmo;
import com.roderly.microbiomelabufu.cadastros_gerais.qualidade_sono.model.QualidadeSono;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

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

    private Long id_consulta;

    private Time horario_deitar;

    private Integer tempo_adormecer;

    private Time horario_levantar;

    private Integer horas_sono;

    @ManyToOne
    @JoinColumn(name = "dificuldade_adormecer", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades dificuldade_adormecer;

    @ManyToOne
    @JoinColumn(name = "acordou_meio_noite", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades acordou_meio_noite;

    @ManyToOne
    @JoinColumn(name = "levantou_banheiro", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades levantou_banheiro;

    @ManyToOne
    @JoinColumn(name = "dificuldade_respirar", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades dificuldade_respirar;

    @ManyToOne
    @JoinColumn(name = "tossiu_roncou", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades tossiu_roncou;

    @ManyToOne
    @JoinColumn(name = "sentiu_frio", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades sentiu_frio;

    @ManyToOne
    @JoinColumn(name = "sentiu_calor", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades sentiu_calor;

    @ManyToOne
    @JoinColumn(name = "sonhos_ruins", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades sonhos_ruins;

    @ManyToOne
    @JoinColumn(name = "teve_dor", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades teve_dor;

    private String outra_dificuldade;

    @ManyToOne
    @JoinColumn(name = "frequencia_outra_dificuldade", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades frequencia_outra_dificuldade;

    @ManyToOne
    @JoinColumn(name = "qualidade_sono", referencedColumnName = "id_qualidade_sono")
    private QualidadeSono qualidadeSono;

    @ManyToOne
    @JoinColumn(name = "medicamento_sono", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades medicamento_sono;

    @ManyToOne
    @JoinColumn(name = "dificuldade_ficar_acordado", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades dificuldade_ficar_acordado;

    @ManyToOne
    @JoinColumn(name = "problema_entusiasmo", referencedColumnName = "id_problema_entusiasmo")
    private ProblemaEntusiasmo problema_entusiasmo;

    @ManyToOne
    @JoinColumn(name = "tem_parceiro_quarto", referencedColumnName = "id_presenca_parceiro")
    private PresencaParceiro tem_parceiro_quarto;

    @ManyToOne
    @JoinColumn(name = "ronco_forte", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades ronco_forte;

    @ManyToOne
    @JoinColumn(name = "paradas_respiracao", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades paradas_respiracao;

    @ManyToOne
    @JoinColumn(name = "contracoes_pernas", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades contracoes_pernas;

    @ManyToOne
    @JoinColumn(name = "desorientacao_sono", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades desorientacao_sono;

    private String outras_alteracoes_sono;

    @ManyToOne
    @JoinColumn(name = "frequencia_outras_alteracoes", referencedColumnName = "id_frequencia_dificuldades")
    private FrequenciaDificuldades frequencia_outras_alteracoes;

}