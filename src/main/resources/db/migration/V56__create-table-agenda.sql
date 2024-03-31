CREATE TABLE agenda
(
    id_evento        BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_evento   BIGINT NOT NULL,
    id_usuario       BIGINT NOT NULL,
    id_status_agenda BIGINT NOT NULL,
    id_paciente      BIGINT,
    data_evento      DATE   NOT NULL,
    hora_inicio      TIME,
    hora_fim         TIME,
    dia_inteiro      TINYINT DEFAULT 0,
    url              VARCHAR(255),
    descricao        TEXT,
    criado_em        DATETIME,
    criado_por_id    BIGINT,
    alterado_em      DATETIME,
    alterado_por_id  BIGINT,
    is_active        TINYINT DEFAULT 1
);
