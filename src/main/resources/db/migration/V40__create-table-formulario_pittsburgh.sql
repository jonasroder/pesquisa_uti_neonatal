CREATE TABLE formulario_pittsburgh
(
    id_formulario_pittsburgh     BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta                  BIGINT NOT NULL, -- ID da consulta associada ao formulário
    horario_deitar               TIME,            -- 1. Hora usual de deitar
    tempo_adormecer              INT,             -- 2. Tempo em minutos para adormecer
    horario_levantar             TIME,            -- 3. Hora usual de levantar
    horas_sono                   INT,             -- 4. Horas de sono por noite
    dificuldade_adormecer        TINYINT,         -- 5a. Dificuldade para adormecer em até 30 minutos
    acordou_meio_noite           TINYINT,         -- 5b. Acordou no meio da noite ou de manhã cedo
    levantou_banheiro            TINYINT,         -- 5c. Precisou levantar para ir ao banheiro
    dificuldade_respirar         TINYINT,         -- 5d. Não conseguiu respirar confortavelmente
    tossiu_roncou                TINYINT,         -- 5e. Tossiu ou roncou forte
    sentiu_frio                  TINYINT,         -- 5f. Sentiu muito frio
    sentiu_calor                 TINYINT,         -- 5g. Sentiu muito calor
    sonhos_ruins                 TINYINT,         -- 5h. Teve sonhos ruins
    teve_dor                     TINYINT,         -- 5i. Teve dor
    outra_dificuldade            VARCHAR(255),    -- 5j. Outra(s) razão(ões) para dificuldade para dormir
    frequencia_outra_dificuldade TINYINT,         -- Frequência da outra dificuldade para dormir
    qualidade_sono               TINYINT,         -- 6. Qualidade geral do sono
    medicamento_sono             TINYINT,         -- 7. Uso de medicamento para ajudar a dormir
    dificuldade_ficar_acordado   TINYINT,         -- 8. Dificuldade de ficar acordado em atividades
    problema_entusiasmo          TINYINT,         -- 9. Problema em manter o entusiasmo para fazer as coisas
    tem_parceiro_quarto          TINYINT,         -- 10. Presença de parceiro ou colega de quarto
    ronco_forte                  TINYINT,         -- Parceiro: Ronco forte
    paradas_respiracao           TINYINT,         -- Parceiro: Longas paradas na respiração enquanto dormia
    contracoes_pernas            TINYINT,         -- Parceiro: Contrações ou puxões nas pernas
    desorientacao_sono           TINYINT,         -- Parceiro: Desorientação ou confusão durante o sono
    outras_alteracoes_sono       VARCHAR(255),    -- Parceiro: Outras alterações durante o sono
    frequencia_outras_alteracoes TINYINT,         -- Frequência das outras alterações durante o sono
    criado_em                    DATETIME,
    criado_por_id                BIGINT,
    alterado_em                  DATETIME,
    alterado_por_id              BIGINT
);
