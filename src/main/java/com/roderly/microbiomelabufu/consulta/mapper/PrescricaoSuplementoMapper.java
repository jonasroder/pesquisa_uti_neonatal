package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.Suplemento;
import com.roderly.microbiomelabufu.consulta.dto.request.PrescricaoSuplementoRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.PrescricaoSuplementoResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.consulta.model.PrescricaoSuplemento;

import java.util.Set;
import java.util.stream.Collectors;

public class PrescricaoSuplementoMapper {
    public static PrescricaoSuplemento prescricaoSuplementoRequestToPrescricaoSuplemento(Consulta consulta, PrescricaoSuplementoRequest request) {
        PrescricaoSuplemento prescricaoSuplemento = new PrescricaoSuplemento();
        Frequencia frequencia = request.id_frequencia() != null ? new Frequencia(request.id_frequencia()) : null;
        Periodo periodo = request.id_periodo() != null ? new Periodo(request.id_periodo()) : null;
        Suplemento suplemento = request.id_suplemento() != null ? new Suplemento(request.id_suplemento()) : null;

        prescricaoSuplemento.setId_prescricao_suplemento(request.id_prescricao_suplemento());
        prescricaoSuplemento.setConsulta(consulta);
        prescricaoSuplemento.setSuplemento(suplemento);
        prescricaoSuplemento.setDosagem(request.dosagem());
        prescricaoSuplemento.setInstrucoes(request.instrucoes());
        prescricaoSuplemento.setFrequencia(frequencia);
        prescricaoSuplemento.setPeriodo(periodo);
        prescricaoSuplemento.setIs_active(request.is_active());

        return prescricaoSuplemento;
    }

    public static Set<PrescricaoSuplementoResponse> prescricaoSuplementoToPrescricaoSuplementoResponse(Set<PrescricaoSuplemento> prescricaoSuplementos) {
        return prescricaoSuplementos.stream().map(prescricaoSuplementoResponse -> {
            return new PrescricaoSuplementoResponse(
                    prescricaoSuplementoResponse.getId_prescricao_suplemento(),
                    prescricaoSuplementoResponse.getConsulta() != null ? prescricaoSuplementoResponse.getConsulta().getId_consulta() : null,
                    prescricaoSuplementoResponse.getSuplemento() != null ? prescricaoSuplementoResponse.getSuplemento().getId_suplemento() : null,
                    prescricaoSuplementoResponse.getDosagem(),
                    prescricaoSuplementoResponse.getInstrucoes(),
                    prescricaoSuplementoResponse.getFrequencia() != null ? prescricaoSuplementoResponse.getFrequencia().getId_frequencia() : null,
                    prescricaoSuplementoResponse.getPeriodo() != null ? prescricaoSuplementoResponse.getPeriodo().getId_periodo() : null,
                    prescricaoSuplementoResponse.getIs_active()
            );
        }).collect(Collectors.toSet());
    }
}
