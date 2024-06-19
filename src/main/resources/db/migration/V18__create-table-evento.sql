CREATE TABLE evento
(
    id_evento       BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_evento  BIGINT NOT NULL,
    id_neonato      BIGINT,
    data_evento     DATE   NOT NULL,
    hora_inicio     TIME,
    hora_fim        TIME,
    dia_inteiro     TINYINT DEFAULT 0,
    observacao      TEXT,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);
