package com.roderly.microbiomelabufu.formulario_alimentacao.mapper;

import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.formulario_alimentacao.dto.request.FormularioAlimentarRequest;
import com.roderly.microbiomelabufu.formulario_alimentacao.dto.response.FormularioAlimentarResponse;
import com.roderly.microbiomelabufu.formulario_alimentacao.model.*;

public class FormularioAlimentarMapper {
    public static FormularioAlimentar formularioAlimentarRequestToFormularioAlimentar(FormularioAlimentarRequest request) {
        var formulario = new FormularioAlimentar();
        var consulta = request.id_consulta() != null ? new Consulta(request.id_consulta()) : null;
        var q15 = request.q15() != null ? new OptionsFrequenciaFormularioAlimentar(request.q15()) : null;
        var q16 = request.q16() != null ? new OptionsFrequenciaFormularioAlimentar(request.q16()) : null;
        var q17 = request.q17() != null ? new OptionsFrequenciaFormularioAlimentar(request.q17()) : null;
        var q18 = request.q18() != null ? new OptionsQ18FormularioAlimentar(request.q18()) : null;
        var q19 = request.q19() != null ? new OptionsFrequenciaFormularioAlimentar(request.q19()) : null;
        var q20 = request.q20() != null ? new OptionsQ20FormularioAlimentar(request.q20()) : null;
        var q25 = request.q25() != null ? new OptionsFrequenciaFormularioAlimentar(request.q25()) : null;
        var q26 = request.q26() != null ? new OptionsQ26FormularioAlimentar(request.q26()) : null;
        var q27 = request.q27() != null ? new OptionsFrequenciaFormularioAlimentar(request.q27()) : null;
        var q28 = request.q28() != null ? new OptionsQ28FormularioAlimentar(request.q28()) : null;
        var q29 = request.q29() != null ? new OptionsFrequenciaFormularioAlimentar(request.q29()) : null;
        var q30 = request.q30() != null ? new OptionsQ30FormularioAlimentar(request.q30()) : null;
        var q31 = request.q31() != null ? new OptionsQ31FormularioAlimentar(request.q31()) : null;

        formulario.setId_formulario_alimentar(request.id_formulario_alimentar());
        formulario.setConsulta(consulta);
        formulario.setQ15(q15);
        formulario.setQ16(q16);
        formulario.setQ17(q17);
        formulario.setQ18(q18);
        formulario.setQ19(q19);
        formulario.setQ20(q20);
        formulario.setQ25(q25);
        formulario.setQ26(q26);
        formulario.setQ27(q27);
        formulario.setQ28(q28);
        formulario.setQ29(q29);
        formulario.setQ30(q30);
        formulario.setQ31(q31);
        formulario.setR301A(request.R301A());
        formulario.setR301B(request.R301B());
        formulario.setR301C(request.R301C());
        formulario.setR301D(request.R301D());
        formulario.setR301E(request.R301E());
        formulario.setR301F(request.R301F());
        formulario.setR301G(request.R301G());
        formulario.setR301H(request.R301H());
        formulario.setR301I(request.R301I());
        formulario.setR301J(request.R301J());
        formulario.setR301K(request.R301K());
        formulario.setR301L(request.R301L());
        formulario.setR302A(request.R302A());
        formulario.setR302B(request.R302B());
        formulario.setR302C(request.R302C());
        formulario.setR302D(request.R302D());
        formulario.setR302E(request.R302E());
        formulario.setR302F(request.R302F());
        formulario.setR302G(request.R302G());
        formulario.setR302H(request.R302H());
        formulario.setR302I(request.R302I());
        formulario.setR302J(request.R302J());
        formulario.setR302K(request.R302K());
        formulario.setR302L(request.R302L());
        formulario.setR302M(request.R302M());

        return formulario;
    }


    public static FormularioAlimentarResponse formularioAlimentarToFormularioAlimentarResponse(FormularioAlimentar formulario) {
        return new FormularioAlimentarResponse(
                formulario.getId_formulario_alimentar(),
                formulario.getConsulta() != null ? formulario.getConsulta().getId_consulta() : null,
                formulario.getQ15() != null ? formulario.getQ15().getId_options_frequencia_formulario_alimentar() : null,
                formulario.getQ16() != null ? formulario.getQ16().getId_options_frequencia_formulario_alimentar() : null,
                formulario.getQ17() != null ? formulario.getQ17().getId_options_frequencia_formulario_alimentar() : null,
                formulario.getQ18() != null ? formulario.getQ18().getId_options_q18() : null,
                formulario.getQ19() != null ? formulario.getQ19().getId_options_frequencia_formulario_alimentar() : null,
                formulario.getQ20() != null ? formulario.getQ20().getId_options_q20() : null,
                formulario.getQ25() != null ? formulario.getQ25().getId_options_frequencia_formulario_alimentar() : null,
                formulario.getQ26() != null ? formulario.getQ26().getId_options_q26() : null,
                formulario.getQ27() != null ? formulario.getQ27().getId_options_frequencia_formulario_alimentar() : null,
                formulario.getQ28() != null ? formulario.getQ28().getId_options_q28() : null,
                formulario.getQ29() != null ? formulario.getQ29().getId_options_frequencia_formulario_alimentar() : null,
                formulario.getQ30() != null ? formulario.getQ30().getId_options_q30() : null,
                formulario.getQ31() != null ? formulario.getQ31().getId_options_q31() : null,
                formulario.getR301A(),
                formulario.getR301B(),
                formulario.getR301C(),
                formulario.getR301D(),
                formulario.getR301E(),
                formulario.getR301F(),
                formulario.getR301G(),
                formulario.getR301H(),
                formulario.getR301I(),
                formulario.getR301J(),
                formulario.getR301K(),
                formulario.getR301L(),
                formulario.getR302A(),
                formulario.getR302B(),
                formulario.getR302C(),
                formulario.getR302D(),
                formulario.getR302E(),
                formulario.getR302F(),
                formulario.getR302G(),
                formulario.getR302H(),
                formulario.getR302I(),
                formulario.getR302J(),
                formulario.getR302K(),
                formulario.getR302L(),
                formulario.getR302M()
        );
    }

}
