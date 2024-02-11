CREATE TABLE paciente_suplemento
(
    id_paciente_suplemento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta            BIGINT NOT NULL,
    id_suplemento          BIGINT NOT NULL,
    dosagem                VARCHAR(255),
    frequencia             VARCHAR(255),
    periodo                VARCHAR(255),
    is_active              TINYINT DEFAULT 1
);
