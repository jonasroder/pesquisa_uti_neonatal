package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import com.roderly.microbiomelabufu.consulta.dto.request.PacienteMedicamentoRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.PacienteMedicamentoResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.consulta.model.PacienteMedicamento;

import java.util.Set;
import java.util.stream.Collectors;

public class PacienteMedicamentoMapper {
    public static PacienteMedicamento pacienteMedicamentoRequestToPacienteMedicamento(Consulta consulta, PacienteMedicamentoRequest request) {
        PacienteMedicamento pacienteMedicamento = new PacienteMedicamento();
        Frequencia frequencia = request.id_frequencia() != null ? new Frequencia(request.id_frequencia()) : null;
        Periodo periodo = request.id_periodo() != null ? new Periodo(request.id_periodo()) : null;
        Medicamento medicamento = request.id_medicamento() != null ? new Medicamento(request.id_medicamento()) : null;

        pacienteMedicamento.setId_paciente_medicamento(request.id_paciente_medicamento());
        pacienteMedicamento.setConsulta(consulta);
        pacienteMedicamento.setMedicamento(medicamento);
        pacienteMedicamento.setDosagem(request.dosagem());
        pacienteMedicamento.setIs_active(request.is_active());
        pacienteMedicamento.setFrequencia(frequencia);
        pacienteMedicamento.setPeriodo(periodo);

        return pacienteMedicamento;
    }

    public static Set<PacienteMedicamentoResponse> pacienteMedicamentoToPacienteMedicamentoResponse(Set<PacienteMedicamento> pacienteMedicamentos) {
        return pacienteMedicamentos.stream().map(pacienteMedicamento -> {
            return new PacienteMedicamentoResponse(
                    pacienteMedicamento.getId_paciente_medicamento(),
                    pacienteMedicamento.getConsulta() != null ? pacienteMedicamento.getConsulta().getId_consulta() : null,
                    pacienteMedicamento.getMedicamento() != null ? pacienteMedicamento.getMedicamento().getId_medicamento() : null,
                    pacienteMedicamento.getDosagem(),
                    pacienteMedicamento.getFrequencia() != null ? pacienteMedicamento.getFrequencia().getId_frequencia() : null,
                    pacienteMedicamento.getPeriodo() != null ? pacienteMedicamento.getPeriodo().getId_periodo() : null,
                    pacienteMedicamento.getIs_active()
            );
        }).collect(Collectors.toSet());
    }
}
