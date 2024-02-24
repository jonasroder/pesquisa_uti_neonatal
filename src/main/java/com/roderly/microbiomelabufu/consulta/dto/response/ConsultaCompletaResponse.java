package com.roderly.microbiomelabufu.consulta.dto.response;

import java.util.List;
import java.util.Set;

public record ConsultaCompletaResponse(
        ConsultaResponse consultaResponse,
        PacienteConsultaResponse pacienteConsultaResponse,
        Set<PacienteMedicamentoResponse> pacienteMedicamentos,
        Set<PacienteSuplementoResponse> pacienteSuplementos,
        Set<ConsultaInformacaoSaudeResponse> consultaInformacoesSaude,
        Set<ConsultaDiagnosticoResponse> consultaDiagnosticos,
        Set<PrescricaoMedicamentoResponse> prescricaoMedicamentos,
        Set<PrescricaoSuplementoResponse> prescricaoSuplementos
) {
}
