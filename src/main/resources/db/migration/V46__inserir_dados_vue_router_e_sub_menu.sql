INSERT INTO vue_router (`path`, name, component, title, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active) VALUES ('/', 'Dashboard-Home', 'dashboard-page/index.vue', 'Dashboard', '2024-12-09 18:08:48', 1, '2024-12-09 18:08:48', 1, 1);
INSERT INTO menu (id_vue_router, icon, title, activator, ordem, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active, id_role) VALUES (LAST_INSERT_ID(), 'fas fa-chart-line', 'Dashboard', NULL, 0, NULL, NULL, NULL, NULL, 1, 2);

UPDATE vue_router SET `path`='/neonato/index' WHERE id_vue_router=1;
