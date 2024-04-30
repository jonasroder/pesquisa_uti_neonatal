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

INSERT INTO menu (id_vue_router, icon, title, activator, ordem, is_active)
VALUES (1, 'fas fa-home', 'Início', NULL, 1, 1);
INSERT INTO menu (id_vue_router, icon, title, activator, ordem, is_active)
VALUES (4, 'fas fa-user', 'Pacientes', NULL, 2, 1);
INSERT INTO menu (id_vue_router, icon, title, activator, ordem, is_active)
VALUES (NULL, 'fas fa-cogs', 'Cadastros', 'a1', 3, 1);

