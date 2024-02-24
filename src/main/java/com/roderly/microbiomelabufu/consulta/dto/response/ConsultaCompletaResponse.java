package com.roderly.microbiomelabufu.consulta.dto.response;

import java.util.List;

public record ConsultaCompletaResponse(
        PacienteConsultaResponse pacienteConsultaResponse,
        List<PacienteMedicamentoResponse> pacienteMedicamentos
//        List<PacienteSuplementoResponse> pacienteSuplementos,
//        List<ConsultaInformacaoSaudeResponse> consultaInformacoesSaude,
//        List<ConsultaDiagnosticoResponse> consultaDiagnosticos,
//        List<PrescricaoMedicamentoResponse> prescricaoMedicamentos,
//        List<PrescricaoSuplementoResponse> prescricaoSuplementos
) {
}
