package com.roderly.microbiomelabufu.consulta.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.frequencia.model.Frequencia;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.periodo.model.Periodo;
import com.roderly.microbiomelabufu.consulta.dto.request.PrescricaoMedicamentoRequest;
import com.roderly.microbiomelabufu.consulta.dto.response.PrescricaoMedicamentoResponse;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import com.roderly.microbiomelabufu.consulta.model.PrescricaoMedicamento;

import java.util.Set;
import java.util.stream.Collectors;

public class PrescricaoMedicamentoMapper {
    public static PrescricaoMedicamento prescricaoMedicamentoRequestToPrescricaoMedicamento(Consulta consulta, PrescricaoMedicamentoRequest request) {
        PrescricaoMedicamento prescricaoMedicamento = new PrescricaoMedicamento();
        Frequencia frequencia = request.id_frequencia() != null ? new Frequencia(request.id_frequencia()) : null;
        Periodo periodo = request.id_periodo() != null ? new Periodo(request.id_periodo()) : null;
        Medicamento medicamento = request.id_medicamento() != null ? new Medicamento(request.id_medicamento()) : null;

        prescricaoMedicamento.setId_prescricao_medicamento(request.id_prescricao_medicamento());
        prescricaoMedicamento.setConsulta(consulta);
        prescricaoMedicamento.setMedicamento(medicamento);
        prescricaoMedicamento.setDosagem(request.dosagem());
        prescricaoMedicamento.setInstrucoes(request.instrucoes());
        prescricaoMedicamento.setFrequencia(frequencia);
        prescricaoMedicamento.setPeriodo(periodo);
        prescricaoMedicamento.setIs_active(request.is_active());

        return prescricaoMedicamento;
    }

    public static Set<PrescricaoMedicamentoResponse> prescricaoMedicamentoToPrescricaoMedicamentoResponse(Set<PrescricaoMedicamento> prescricaoMedicamentos) {
        return prescricaoMedicamentos.stream().map(prescricaoMedicamento -> {
            return new PrescricaoMedicamentoResponse(
                    prescricaoMedicamento.getId_prescricao_medicamento(),
                    prescricaoMedicamento.getConsulta() != null ? prescricaoMedicamento.getConsulta().getId_consulta() : null,
                    prescricaoMedicamento.getMedicamento() != null ? prescricaoMedicamento.getMedicamento().getId_medicamento() : null,
                    prescricaoMedicamento.getDosagem(),
                    prescricaoMedicamento.getInstrucoes(),
                    prescricaoMedicamento.getFrequencia() != null ? prescricaoMedicamento.getFrequencia().getId_frequencia() : null,
                    prescricaoMedicamento.getPeriodo() != null ? prescricaoMedicamento.getPeriodo().getId_periodo() : null,
                    prescricaoMedicamento.getIs_active()
            );
        }).collect(Collectors.toSet());
    }
}
