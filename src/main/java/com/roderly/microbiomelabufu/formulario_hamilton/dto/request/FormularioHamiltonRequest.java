package com.roderly.microbiomelabufu.formulario_hamilton.dto.request;

public record FormularioHamiltonRequest(
        Long id_formulario_hamilton,
        Long id_consulta,
        Long id_humor_ansioso,
        Long id_tensao,
        Long id_medos,
        Long id_insonia,
        Long id_dificuldades_intelectuais,
        Long id_humor_deprimido,
        Long id_somatizacoes_motoras,
        Long id_somatizacoes_sensoriais,
        Long id_sintomas_cardiovasculares,
        Long id_sintomas_respiratorios,
        Long id_sintomas_gastrointestinais,
        Long id_sintomas_geniturinarios,
        Long id_sintomas_neurovegetativos,
        Long id_comportamento_entrevista
) {
}
