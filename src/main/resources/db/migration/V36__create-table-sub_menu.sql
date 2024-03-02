CREATE TABLE sub_menu
(
    id_sub_menu   BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_menu       BIGINT,
    id_vue_router BIGINT,
    title         VARCHAR(250),
    is_active     TINYINT DEFAULT 1
);

INSERT INTO sub_menu (id_menu, id_vue_router, title, is_active) VALUES(3, 6, 'Medicamentos', 1);
INSERT INTO sub_menu (id_menu, id_vue_router, title, is_active) VALUES(3, 11, 'Suplementos', 1);
INSERT INTO sub_menu (id_menu, id_vue_router, title, is_active) VALUES(3, 14, 'Diagnósticos', 1);
INSERT INTO sub_menu (id_menu, id_vue_router, title, is_active) VALUES(3, 15, 'Informação Saúde', 1);
INSERT INTO sub_menu (id_menu, id_vue_router, title, is_active) VALUES(3, 12, 'Ingredientes', 1);

