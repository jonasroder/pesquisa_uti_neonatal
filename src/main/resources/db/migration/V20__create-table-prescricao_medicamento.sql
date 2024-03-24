CREATE TABLE prescricao_medicamento
(
    id_prescricao_medicamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta               BIGINT,
    id_medicamento            BIGINT,
    dosagem                   VARCHAR(255),
    instrucoes                TEXT,
    id_frequencia             BIGINT,
    id_periodo                BIGINT,
    criado_em                 DATETIME,
    criado_por_id             BIGINT,
    alterado_em               DATETIME,
    alterado_por_id           BIGINT,
    is_active                 TINYINT DEFAULT 1
);
