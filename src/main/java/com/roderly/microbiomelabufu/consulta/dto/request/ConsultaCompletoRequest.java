package com.roderly.microbiomelabufu.consulta.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public record ConsultaCompletoRequest(
        Long id_paciente,
        Long id_consulta,
        Long id_tipo_consulta,
        LocalDateTime data_hora,
        String observacoes,
        String sintomas,
        List<ConsultaDiagnosticoRequest> arrConsultaDiagnostico,
        List<PacienteMedicamentoRequest> arrMedicamentoUsoPaciente,
        List<PacienteSuplementoRequest> arrSuplementoUsoPaciente,
        List<ConsultaInformacaoSaudeRequest> arrInformacaoSaude,
        List<PrescricaoMedicamentoRequest> arrPrescricaoMedicamento,
        List<PrescricaoSuplementoRequest> arrPrescricaoSuplemento,
        List<ArquivoConsultaRequest> arrConsultaAnexos
) {
}
