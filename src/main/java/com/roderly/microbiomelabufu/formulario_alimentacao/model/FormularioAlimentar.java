package com.roderly.microbiomelabufu.formulario_alimentacao.model;

import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.formulario_pittsburgh.model.FrequenciaDificuldades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formulario_alimentar")
@Entity(name = "FormularioAlimentar")
public class FormularioAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formulario_alimentar;

    @OneToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q15", referencedColumnName = "id_options_frequencia_formulario_alimentar")
    private OptionsFrequenciaFormularioAlimentar q15;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q16", referencedColumnName = "id_options_frequencia_formulario_alimentar")
    private OptionsFrequenciaFormularioAlimentar q16;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q17", referencedColumnName = "id_options_frequencia_formulario_alimentar")
    private OptionsFrequenciaFormularioAlimentar q17;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q18", referencedColumnName = "id_options_q18")
    private OptionsQ18FormularioAlimentar q18;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q19", referencedColumnName = "id_options_frequencia_formulario_alimentar")
    private OptionsFrequenciaFormularioAlimentar q19;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q20", referencedColumnName = "id_options_q20")
    private OptionsQ20FormularioAlimentar q20;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q25", referencedColumnName = "id_options_frequencia_formulario_alimentar")
    private OptionsFrequenciaFormularioAlimentar q25;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q26", referencedColumnName = "id_options_q26")
    private OptionsQ26FormularioAlimentar q26;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q27", referencedColumnName = "id_options_frequencia_formulario_alimentar")
    private OptionsFrequenciaFormularioAlimentar q27;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q28", referencedColumnName = "id_options_q28")
    private OptionsQ28FormularioAlimentar q28;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q29", referencedColumnName = "id_options_frequencia_formulario_alimentar")
    private OptionsFrequenciaFormularioAlimentar q29;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q30", referencedColumnName = "id_options_q30")
    private OptionsQ30FormularioAlimentar q30;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q31", referencedColumnName = "id_options_q31")
    private OptionsQ31FormularioAlimentar q31;

    Boolean R301A;
    Boolean R301B;
    Boolean R301C;
    Boolean R301D;
    Boolean R301E;
    Boolean R301F;
    Boolean R301G;
    Boolean R301H;
    Boolean R301I;
    Boolean R301J;
    Boolean R301K;
    Boolean R301L;
    Boolean R302A;
    Boolean R302B;
    Boolean R302C;
    Boolean R302D;
    Boolean R302E;
    Boolean R302F;
    Boolean R302G;
    Boolean R302H;
    Boolean R302I;
    Boolean R302J;
    Boolean R302K;
    Boolean R302L;
    Boolean R302M;
}
