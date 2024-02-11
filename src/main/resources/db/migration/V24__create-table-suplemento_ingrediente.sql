CREATE TABLE suplemento_ingrediente
(
    id_suplemento_ingrediente BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_suplemento             BIGINT,
    id_ingrediente            BIGINT,
    dosagem                   VARCHAR(255),
    is_active                 TINYINT DEFAULT 1
);
