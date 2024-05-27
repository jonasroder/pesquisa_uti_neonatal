CREATE TABLE evento_entidade
(
    id_evento_entidade BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_evento          BIGINT,
    tipo_entidade      VARCHAR(50),
    id_entidade        BIGINT,
    criado_em          DATETIME,
    criado_por_id      BIGINT,
    alterado_em        DATETIME,
    alterado_por_id    BIGINT,
    is_active          TINYINT DEFAULT 1
);

