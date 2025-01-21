
-- Inserindo novos registros na tabela vue_router
INSERT INTO vue_router (id_vue_router, path,                           name,                        component,                                        title,                          is_active, criado_em, alterado_em, criado_por_id, alterado_por_id)
VALUES                 (18,            '/motivo_internacao/edit',  'MotivoInternacao-Page', 'cadastros/motivo_internacao-page/edit.vue',  'Motivo Internação',       1,         NOW(),     NOW(),       1,             1              ),
                       (19,            '/motivo_internacao/index', 'MotivoInternacao-List', 'cadastros/motivo_internacao-page/index.vue', 'Lista Motivo Internação', 1,         NOW(),     NOW(),       1,             1              );


-- Inserindo novos registros na tabela sub_menu
INSERT INTO sub_menu (id_menu, id_vue_router, title,                    is_active)
VALUES               (1,       19,            'Motivo Internação',    1        );
