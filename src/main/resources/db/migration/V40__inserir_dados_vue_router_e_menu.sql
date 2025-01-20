
INSERT INTO vue_router (`path`, name, component, title, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES ('/download/index', 'Download-Page', 'download-page/index.vue', 'Downloads', now(), 1, null, null, 1);


INSERT INTO menu (id_vue_router, icon, title, activator, ordem, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (1, 'fas fa-download', 'Downloads', NULL, 3, NULL, NULL, NULL, NULL, 1);


ALTER TABLE menu
    ADD id_role BIGINT DEFAULT 2;