package com.roderly.microbiomelabufu.formulario_pittsburgh.dto.response;

import java.time.LocalTime;

public record FormularioPittsburghResponse(
        Long id_formulario_pittsburgh,
        Long id_consulta,
        LocalTime horario_deitar,
        Integer tempo_adormecer,
        LocalTime horario_levantar,
        Integer horas_sono,
        Long dificuldade_adormecer,
        Long acordou_meio_noite,
        Long levantou_banheiro,
        Long dificuldade_respirar,
        Long tossiu_roncou,
        Long sentiu_frio,
        Long sentiu_calor,
        Long sonhos_ruins,
        Long teve_dor,
        String outra_dificuldade,
        Long frequencia_outra_dificuldade,
        Long qualidade_sono,
        Long medicamento_sono,
        Long dificuldade_ficar_acordado,
        Long problema_entusiasmo,
        Long tem_parceiro_quarto,
        Long ronco_forte,
        Long paradas_respiracao,
        Long contracoes_pernas,
        Long desorientacao_sono,
        String outras_alteracoes_sono,
        Long frequencia_outras_alteracoes
) {
}
