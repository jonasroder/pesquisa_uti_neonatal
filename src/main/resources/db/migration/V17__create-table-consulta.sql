CREATE TABLE consulta
(
    id_consulta      BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente      BIGINT   NOT NULL,
    id_usuario       BIGINT   NOT NULL,
    id_tipo_consulta BIGINT   NOT NULL,
    data_hora        DATETIME NOT NULL,
    observacoes      TEXT,
    sintomas         TEXT,
    criado_em        DATETIME,
    criado_por_id    BIGINT,
    alterado_em      DATETIME,
    alterado_por_id  BIGINT,
    is_active        TINYINT DEFAULT 1
);
