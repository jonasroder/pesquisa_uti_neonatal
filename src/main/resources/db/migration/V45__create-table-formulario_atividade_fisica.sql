CREATE TABLE formulario_atividade_fisica
(
    id_formulario_atividade_fisica BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta                    BIGINT NOT NULL,
    tempo_atividade_vigorosa       TIME,
    dias_atividade_vigorosa        INT,
    tempo_atividade_moderada       TIME,
    dias_atividade_moderada        INT,
    tempo_caminhada_moderada       TIME,
    dias_caminhada_moderada        INT,
    tempo_sentado_dia_util         TIME,
    tempo_sentado_fds              TIME,
    criado_em                      DATETIME,
    criado_por_id                  BIGINT,
    alterado_em                    DATETIME,
    alterado_por_id                BIGINT
);
