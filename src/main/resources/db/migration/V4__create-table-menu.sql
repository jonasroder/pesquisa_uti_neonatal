CREATE TABLE menu
(
    id_menu         BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_vue_router   BIGINT,
    icon            VARCHAR(250),
    title           VARCHAR(250),
    activator       VARCHAR(250) UNIQUE,
    ordem           BIGINT,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO menu (id_vue_router, icon, title, activator, ordem, criado_em, criado_por_id, alterado_em, alterado_por_id,
                  is_active)
VALUES (NULL, 'fas fa-cogs', 'Cadastros', 'a1', 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO menu (id_vue_router, icon, title, activator, ordem, criado_em, criado_por_id, alterado_em, alterado_por_id,
                  is_active)
VALUES (1, 'fas fa-user', 'Neonatos', NULL, 1, NULL, NULL, NULL, NULL, 1);