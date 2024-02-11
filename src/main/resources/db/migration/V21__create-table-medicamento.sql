CREATE TABLE medicamento
(
    id_medicamento            BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                      VARCHAR(255) NOT NULL,
    dosagem                   VARCHAR(255),
    id_fabricante_medicamento BIGINT,
    instrucoes                TEXT,
    is_active                 TINYINT DEFAULT 1
);
