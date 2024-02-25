package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;
import com.roderly.microbiomelabufu.consulta.dto.request.ConsultaDiagnosticoRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaDiagnosticoResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.consulta.model.ConsultaDiagnostico;

import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaDiagnosticoMapper {
    public static ConsultaDiagnostico consultaDiagnosticoRequestToConsultaDiagnostico(Consulta consulta, ConsultaDiagnosticoRequest request) {
        ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico();
        Diagnostico diagnostico = new Diagnostico(request.id_diagnostico());

        consultaDiagnostico.setId_consulta_diagnostico(request.id_consulta_diagnostico());
        consultaDiagnostico.setConsulta(consulta);
        consultaDiagnostico.setDiagnostico(diagnostico);
        consultaDiagnostico.setIs_active(request.is_active());

        return consultaDiagnostico;
    }


    public static Set<ConsultaDiagnosticoResponse> consultaDiagnosticoToConsutoaDiagnosticoResponse(Set<ConsultaDiagnostico> consultaDiagnosticos) {
        return consultaDiagnosticos.stream().map(consultaDiagnostico -> {
            return new ConsultaDiagnosticoResponse(
                    consultaDiagnostico.getId_consulta_diagnostico(),
                    consultaDiagnostico.getConsulta() != null ? consultaDiagnostico.getConsulta().getId_consulta() : null,
                    consultaDiagnostico.getDiagnostico() != null ? consultaDiagnostico.getDiagnostico().getId_diagnostico() : null,
                    consultaDiagnostico.getIs_active()
            );
        }).collect(Collectors.toSet());
    }
}
