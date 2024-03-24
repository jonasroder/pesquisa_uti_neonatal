CREATE TABLE agenda
(
    id_evento        INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_evento   INT      NOT NULL,
    id_usuario       INT      NOT NULL,
    id_status_agenda INT      NOT NULL,
    inicio           DATETIME NOT NULL,
    fim              DATETIME,
    dia_inteiro      TINYINT DEFAULT 0,
    url              VARCHAR(255),
    descricao        TEXT,
    criado_em        DATETIME,
    criado_por_id    BIGINT,
    alterado_em      DATETIME,
    alterado_por_id  BIGINT,
    is_active        TINYINT DEFAULT 1
);
