CREATE TABLE suplemento_ingrediente
(
    id_suplemento_ingrediente BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_suplemento             BIGINT,
    id_ingrediente            BIGINT,
    dosagem                   VARCHAR(255),
    criado_em                 DATETIME,
    criado_por_id             BIGINT,
    alterado_em               DATETIME,
    alterado_por_id           BIGINT,
    is_active                 TINYINT DEFAULT 1
);
