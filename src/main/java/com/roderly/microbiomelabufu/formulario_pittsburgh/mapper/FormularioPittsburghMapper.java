package com.roderly.microbiomelabufu.formulario_pittsburgh.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia_dificuldades.model.FrequenciaDificuldades;
import com.roderly.microbiomelabufu.cadastros_gerais.presenca_parceiro.model.PresencaParceiro;
import com.roderly.microbiomelabufu.cadastros_gerais.problema_entusiasmo.model.ProblemaEntusiasmo;
import com.roderly.microbiomelabufu.cadastros_gerais.qualidade_sono.model.QualidadeSono;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.request.FormularioPittsburghRequest;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.response.FormularioPittsburghResponse;
import com.roderly.microbiomelabufu.formulario_pittsburgh.model.FormularioPittsburgh;

public class FormularioPittsburghMapper {

    public static FormularioPittsburgh formularioPittsburghRequestToFormularioPittsburgh(FormularioPittsburghRequest request){
        var formulario = new FormularioPittsburgh();
        var consuta = request.id_consulta() != null ? new Consulta(request.id_consulta()) : null;
        var dificuldade_adormecer = request.dificuldade_adormecer() != null ? new FrequenciaDificuldades(request.dificuldade_adormecer()) : null;
        var acordou_meio_noite = request.acordou_meio_noite() != null ? new FrequenciaDificuldades(request.acordou_meio_noite()) : null;
        var levantou_banheiro = request.levantou_banheiro() != null ? new FrequenciaDificuldades(request.levantou_banheiro()) : null;
        var dificuldade_respirar = request.dificuldade_respirar() != null ? new FrequenciaDificuldades(request.dificuldade_respirar()) : null;
        var tossiu_roncou = request.tossiu_roncou() != null ? new FrequenciaDificuldades(request.tossiu_roncou()) : null;
        var sentiu_frio = request.sentiu_frio() != null ? new FrequenciaDificuldades(request.sentiu_frio()) : null;
        var sentiu_calor = request.sentiu_calor() != null ? new FrequenciaDificuldades(request.sentiu_calor()) : null;
        var sonhos_ruins = request.sonhos_ruins() != null ? new FrequenciaDificuldades(request.sonhos_ruins()) : null;
        var teve_dor = request.teve_dor() != null ? new FrequenciaDificuldades(request.teve_dor()) : null;
        var frequencia_outra_dificuldade = request.frequencia_outra_dificuldade() != null ? new FrequenciaDificuldades(request.frequencia_outra_dificuldade()) : null;
        var qualidade_sono = request.qualidade_sono() != null ? new QualidadeSono(request.qualidade_sono()) : null;
        var medicamento_sono = request.medicamento_sono() != null ? new FrequenciaDificuldades(request.medicamento_sono()) : null;
        var dificuldade_ficar_acordado = request.dificuldade_ficar_acordado() != null ? new FrequenciaDificuldades(request.dificuldade_ficar_acordado()) : null;
        var problema_entusiasmo = request.problema_entusiasmo() != null ? new ProblemaEntusiasmo(request.problema_entusiasmo()) : null;
        var tem_parceiro_quarto = request.tem_parceiro_quarto() != null ? new PresencaParceiro(request.tem_parceiro_quarto()) : null;
        var ronco_forte = request.ronco_forte() != null ? new FrequenciaDificuldades(request.ronco_forte()) : null;
        var paradas_respiracao = request.paradas_respiracao() != null ? new FrequenciaDificuldades(request.paradas_respiracao()) : null;
        var contracoes_pernas = request.contracoes_pernas() != null ? new FrequenciaDificuldades(request.contracoes_pernas()) : null;
        var desorientacao_sono = request.desorientacao_sono() != null ? new FrequenciaDificuldades(request.desorientacao_sono()) : null;
        var frequencia_outras_alteracoes = request.frequencia_outras_alteracoes() != null ? new FrequenciaDificuldades(request.frequencia_outras_alteracoes()) : null;

        formulario.setId_formulario_pittsburgh(request.id_formulario_pittsburgh());
        formulario.setConsulta(consuta);
        formulario.setHorario_deitar(request.horario_deitar());
        formulario.setTempo_adormecer(request.tempo_adormecer());
        formulario.setHorario_levantar(request.horario_levantar());
        formulario.setHoras_sono(request.horas_sono());
        formulario.setDificuldade_adormecer(dificuldade_adormecer);
        formulario.setAcordou_meio_noite(acordou_meio_noite);
        formulario.setLevantou_banheiro(levantou_banheiro);
        formulario.setDificuldade_respirar(dificuldade_respirar);
        formulario.setTossiu_roncou(tossiu_roncou);
        formulario.setSentiu_frio(sentiu_frio);
        formulario.setSentiu_calor(sentiu_calor);
        formulario.setSonhos_ruins(sonhos_ruins);
        formulario.setTeve_dor(teve_dor);
        formulario.setOutra_dificuldade(request.outra_dificuldade());
        formulario.setFrequencia_outra_dificuldade(frequencia_outra_dificuldade);
        formulario.setQualidadeSono(qualidade_sono);
        formulario.setMedicamento_sono(medicamento_sono);
        formulario.setDificuldade_ficar_acordado(dificuldade_ficar_acordado);
        formulario.setProblema_entusiasmo(problema_entusiasmo);
        formulario.setTem_parceiro_quarto(tem_parceiro_quarto);
        formulario.setRonco_forte(ronco_forte);
        formulario.setParadas_respiracao(paradas_respiracao);
        formulario.setContracoes_pernas(contracoes_pernas);
        formulario.setDesorientacao_sono(desorientacao_sono);
        formulario.setOutras_alteracoes_sono(request.outras_alteracoes_sono());
        formulario.setFrequencia_outras_alteracoes(frequencia_outras_alteracoes);

        return formulario;
    }


    public static FormularioPittsburghResponse formularioPittsburghToFormularioPittsburghResponse(FormularioPittsburgh formulario){
        return new FormularioPittsburghResponse(
                formulario.getId_formulario_pittsburgh(),
                formulario.getConsulta().getId_consulta(),
                formulario.getHorario_deitar(),
                formulario.getTempo_adormecer(),
                formulario.getHorario_levantar(),
                formulario.getHoras_sono(),
                formulario.getDificuldade_adormecer() != null ? formulario.getDificuldade_adormecer().getId_frequencia_dificuldades() : null,
                formulario.getAcordou_meio_noite() != null ? formulario.getAcordou_meio_noite().getId_frequencia_dificuldades() : null,
                formulario.getLevantou_banheiro() != null ? formulario.getLevantou_banheiro().getId_frequencia_dificuldades() : null,
                formulario.getDificuldade_respirar() != null ? formulario.getDificuldade_respirar().getId_frequencia_dificuldades() : null,
                formulario.getTossiu_roncou() != null ? formulario.getTossiu_roncou().getId_frequencia_dificuldades() : null,
                formulario.getSentiu_frio() != null ? formulario.getSentiu_frio().getId_frequencia_dificuldades() : null,
                formulario.getSentiu_calor() != null ? formulario.getSentiu_calor().getId_frequencia_dificuldades() : null,
                formulario.getSonhos_ruins() != null ? formulario.getSonhos_ruins().getId_frequencia_dificuldades() : null,
                formulario.getTeve_dor() != null ? formulario.getTeve_dor().getId_frequencia_dificuldades() : null,
                formulario.getOutra_dificuldade(),
                formulario.getFrequencia_outra_dificuldade() != null ? formulario.getFrequencia_outra_dificuldade().getId_frequencia_dificuldades() : null,
                formulario.getQualidadeSono() != null ? formulario.getQualidadeSono().getId_qualidade_sono() : null,
                formulario.getMedicamento_sono() != null ? formulario.getMedicamento_sono().getId_frequencia_dificuldades() : null,
                formulario.getDificuldade_ficar_acordado() != null ? formulario.getDificuldade_ficar_acordado().getId_frequencia_dificuldades() : null,
                formulario.getProblema_entusiasmo() != null ? formulario.getProblema_entusiasmo().getId_problema_entusiasmo() : null,
                formulario.getTem_parceiro_quarto() != null ? formulario.getTem_parceiro_quarto().getId_presenca_parceiro() : null,
                formulario.getRonco_forte() != null ? formulario.getRonco_forte().getId_frequencia_dificuldades() : null,
                formulario.getParadas_respiracao() != null ? formulario.getParadas_respiracao().getId_frequencia_dificuldades() : null,
                formulario.getContracoes_pernas() != null ? formulario.getContracoes_pernas().getId_frequencia_dificuldades() : null,
                formulario.getDesorientacao_sono() != null ? formulario.getDesorientacao_sono().getId_frequencia_dificuldades() : null,
                formulario.getOutras_alteracoes_sono(),
                formulario.getFrequencia_outras_alteracoes() != null ? formulario.getFrequencia_outras_alteracoes().getId_frequencia_dificuldades() : null
        );
    }
}
