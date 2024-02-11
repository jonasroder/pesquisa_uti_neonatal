CREATE TABLE prescricao
(
    id_prescricao     BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta       BIGINT,
    id_medicamento    BIGINT,
    dosagem           VARCHAR(255),
    instrucoes        TEXT,
    inicio_tratamento DATE,
    fim_tratamento    DATE,
    is_active         TINYINT DEFAULT 1
);
