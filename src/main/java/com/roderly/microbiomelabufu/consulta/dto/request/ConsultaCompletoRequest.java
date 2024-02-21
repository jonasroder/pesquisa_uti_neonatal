package com.roderly.microbiomelabufu.consulta.dto.request;

import java.util.List;

public record ConsultaCompletoRequest(
        Long id_paciente,
        Long id_consulta,
        Long id_tipo_consulta,
        String observacoes,
        String sintomas,
        List<Long> consultaDiagnostico,
        List<PacienteMedicamentoRequest> arrMedicamentoUsoPaciente,
        List<PacienteSuplementoRequest> arrSuplementoUsoPaciente,
        List<ConsultaInformacaoSaudeRequest> arrInformacaoSaude,
        List<PrescricaoMedicamentoRequest> arrPrescricaoMedicamento,
        List<PrescricaoSuplementoRequest> arrPrescricaoSuplemento

) {
}
