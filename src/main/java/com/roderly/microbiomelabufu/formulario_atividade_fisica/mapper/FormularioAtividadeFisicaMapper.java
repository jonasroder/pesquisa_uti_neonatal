package com.roderly.microbiomelabufu.formulario_atividade_fisica.mapper;

import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.request.FormularioAtividadeFisicaRequest;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.response.FormularioAtividadeFisicaResponse;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.model.FormularioAtividadeFisica;

public class FormularioAtividadeFisicaMapper {

    public static FormularioAtividadeFisica formularioAtividadeFisicaRequestToFormularioAtividadeFisica(FormularioAtividadeFisicaRequest request){
        var formulario = new FormularioAtividadeFisica();
        var consulta = request.id_consulta() != null ? new Consulta(request.id_consulta()) : null;

        formulario.setId_formulario_atividade_fisica(request.id_formulario_atividade_fisica());
        formulario.setConsulta(consulta);
        formulario.setTempo_atividade_vigorosa(request.tempo_atividade_vigorosa());
        formulario.setDias_atividade_vigorosa(request.dias_atividade_vigorosa());
        formulario.setTempo_atividade_moderada(request.tempo_atividade_moderada());
        formulario.setDias_atividade_moderada(request.dias_atividade_moderada());
        formulario.setTempo_caminhada_moderada(request.tempo_caminhada_moderada());
        formulario.setDias_caminhada_moderada(request.dias_caminhada_moderada());
        formulario.setTempo_sentado_dia_util(request.tempo_sentado_dia_util());
        formulario.setTempo_sentado_fds(request.tempo_sentado_fds());

        return formulario;
    }


    public static FormularioAtividadeFisicaResponse formularioAtividadeFisicaToformularioAtividadeFisicaResponse(FormularioAtividadeFisica formulario){
        return new FormularioAtividadeFisicaResponse(
                formulario.getId_formulario_atividade_fisica(),
                formulario.getConsulta() != null ? formulario.getConsulta().getId_consulta() : null,
                formulario.getTempo_atividade_vigorosa(),
                formulario.getDias_atividade_vigorosa(),
                formulario.getTempo_atividade_moderada(),
                formulario.getDias_atividade_moderada(),
                formulario.getTempo_caminhada_moderada(),
                formulario.getDias_caminhada_moderada(),
                formulario.getTempo_sentado_dia_util(),
                formulario.getTempo_sentado_fds()
        );
    }
}
