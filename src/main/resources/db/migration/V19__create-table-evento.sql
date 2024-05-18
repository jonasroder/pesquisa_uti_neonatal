CREATE TABLE evento
(
    id_evento       BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_evento  BIGINT,
    codigo          BIGINT UNIQUE NOT NULL,
    descricao       VARCHAR(255)  NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);
