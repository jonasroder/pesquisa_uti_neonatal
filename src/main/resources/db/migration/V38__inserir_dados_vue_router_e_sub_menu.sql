UPDATE vue_router
SET title='Tipo Microorganismo'
WHERE id_vue_router = 13;

-- Inserindo novos registros na tabela vue_router
INSERT INTO vue_router (id_vue_router, path,                           name,                        component,                                        title,                          is_active, criado_em, alterado_em, criado_por_id, alterado_por_id)
VALUES                 (15,            '/genero_microorganismo/edit',  'GeneroMicroorganismo-Page', 'cadastros/genero_microorganismo-page/edit.vue',  'Gênero Microorganismo',       1,         NOW(),     NOW(),       1,             1              ),
                       (16,            '/genero_microorganismo/index', 'GeneroMicroorganismo-List', 'cadastros/genero_microorganismo-page/index.vue', 'Lista Gênero Microorganismo', 1,         NOW(),     NOW(),       1,             1              );


-- Inserindo novos registros na tabela sub_menu
INSERT INTO sub_menu (id_menu, id_vue_router, title,                    is_active)
VALUES               (1,       16,            'Gênero Microorganismo',    1        );
