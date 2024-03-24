CREATE TABLE paciente_medicamento
(
    id_paciente_medicamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta             BIGINT NOT NULL,
    id_medicamento          BIGINT NOT NULL,
    dosagem                 VARCHAR(255),
    id_frequencia           BIGINT,
    id_periodo              BIGINT,
    criado_em               DATETIME,
    criado_por_id           BIGINT,
    alterado_em             DATETIME,
    alterado_por_id         BIGINT,
    is_active               TINYINT DEFAULT 1
);
