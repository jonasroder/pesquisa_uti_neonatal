CREATE TABLE sub_menu
(
    id_sub_menu     BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_menu         BIGINT,
    id_vue_router   BIGINT,
    title           VARCHAR(250),
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

