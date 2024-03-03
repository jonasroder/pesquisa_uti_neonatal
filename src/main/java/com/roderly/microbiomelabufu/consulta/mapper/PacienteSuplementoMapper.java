package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.Suplemento;
import com.roderly.microbiomelabufu.consulta.dto.request.PacienteSuplementoRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.PacienteSuplementoResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.consulta.model.PacienteSuplemento;

import java.util.Set;
import java.util.stream.Collectors;

public class PacienteSuplementoMapper {
    public static PacienteSuplemento pacienteSuplementoRequestToPacienteSuplemento(Consulta consulta, PacienteSuplementoRequest request) {
        PacienteSuplemento pacienteSuplemento = new PacienteSuplemento();
        Frequencia frequencia = request.id_frequencia() != null ? new Frequencia(request.id_frequencia()) : null;
        Periodo periodo = request.id_periodo() != null ? new Periodo(request.id_periodo()) : null;
        Suplemento suplemento = request.id_suplemento() != null ? new Suplemento(request.id_suplemento()) : null;

        pacienteSuplemento.setId_paciente_suplemento(request.id_paciente_suplemento());
        pacienteSuplemento.setConsulta(consulta);
        pacienteSuplemento.setSuplemento(suplemento);
        pacienteSuplemento.setDosagem(request.dosagem());
        pacienteSuplemento.setFrequencia(frequencia);
        pacienteSuplemento.setPeriodo(periodo);
        pacienteSuplemento.setIs_active(request.is_active());

        return pacienteSuplemento;
    }

    public static Set<PacienteSuplementoResponse> pacienteSuplementoToPacienteSuplementoResponse(Set<PacienteSuplemento> pacienteSuplementos) {
        return pacienteSuplementos.stream().map(pacienteSuplemento -> {
            return new PacienteSuplementoResponse(
                    pacienteSuplemento.getId_paciente_suplemento(),
                    pacienteSuplemento.getConsulta() != null ? pacienteSuplemento.getConsulta().getId_consulta() : null,
                    pacienteSuplemento.getSuplemento() != null ? pacienteSuplemento.getSuplemento().getId_suplemento() : null,
                    pacienteSuplemento.getDosagem(),
                    pacienteSuplemento.getFrequencia() != null ? pacienteSuplemento.getFrequencia().getId_frequencia() : null,
                    pacienteSuplemento.getPeriodo() != null ? pacienteSuplemento.getPeriodo().getId_periodo() : null,
                    pacienteSuplemento.getIs_active()
            );
        }).collect(Collectors.toSet());
    }
}
