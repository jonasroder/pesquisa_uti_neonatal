CREATE TABLE paciente_medicamento
(
    id_paciente_medicamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta             BIGINT NOT NULL,
    id_medicamento          BIGINT NOT NULL,
    dosagem                 VARCHAR(255),
    frequencia              VARCHAR(255),
    periodo                 VARCHAR(255),
    is_active               TINYINT DEFAULT 1
);
