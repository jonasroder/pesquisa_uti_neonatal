package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.model.TipoInformacaoSaude;
import com.roderly.microbiomelabufu.consulta.dto.request.ConsultaInformacaoSaudeRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaInformacaoSaudeResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.consulta.model.ConsultaInformacaoSaude;

import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaInformacaoSaudeMapper {
    public static ConsultaInformacaoSaude consultaInformacaoSaudeRequestToConsultaInformacaoSaude(Consulta consulta, ConsultaInformacaoSaudeRequest request) {
        ConsultaInformacaoSaude consultaInformacaoSaude = new ConsultaInformacaoSaude();
        TipoInformacaoSaude tipoInformacaoSaude = request.id_tipo_informacao_saude() != null ? new TipoInformacaoSaude(request.id_tipo_informacao_saude()) : null;

        consultaInformacaoSaude.setId_consulta_informacao_saude(request.id_consulta_informacao_saude());
        consultaInformacaoSaude.setConsulta(consulta);
        consultaInformacaoSaude.setValor(request.valor());
        consultaInformacaoSaude.setTipoInformacaoSaude(tipoInformacaoSaude);
        consultaInformacaoSaude.setIs_active(request.is_active());

        return consultaInformacaoSaude;
    }

    public static Set<ConsultaInformacaoSaudeResponse> consultaInformacaoSaudeToConsultaInformacaoSaudeResponse(Set<ConsultaInformacaoSaude> consultaInformacoesSaude) {
        return consultaInformacoesSaude.stream().map(consultaInformacaoSaude -> {
            return new ConsultaInformacaoSaudeResponse(
                    consultaInformacaoSaude.getId_consulta_informacao_saude(),
                    consultaInformacaoSaude.getConsulta() != null ? consultaInformacaoSaude.getConsulta().getId_consulta() : null,
                    consultaInformacaoSaude.getTipoInformacaoSaude() != null ? consultaInformacaoSaude.getTipoInformacaoSaude().getId_tipo_informacao_saude() : null,
                    consultaInformacaoSaude.getValor(),
                    consultaInformacaoSaude.getIs_active()
            );
        }).collect(Collectors.toSet());
    }
}
