CREATE TABLE formulario_hamilton
(
    id_formulario_hamilton     BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta                BIGINT  NOT NULL,
    humor_ansioso              TINYINT NOT NULL,
    tensao                     TINYINT NOT NULL,
    medos                      TINYINT NOT NULL,
    insonia                    TINYINT NOT NULL,
    dificuldades_intelectuais  TINYINT NOT NULL,
    humor_deprimido            TINYINT NOT NULL,
    somatizacoes_motoras       TINYINT NOT NULL,
    somatizacoes_sensoriais    TINYINT NOT NULL,
    sintomas_cardiovasculares  TINYINT NOT NULL,
    sintomas_respiratorios     TINYINT NOT NULL,
    sintomas_gastrointestinais TINYINT NOT NULL,
    sintomas_geniturinarios    TINYINT NOT NULL,
    sintomas_neurovegetativos  TINYINT NOT NULL,
    comportamento_entrevista   TINYINT NOT NULL
);
