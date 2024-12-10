-- Inserindo novos registros na tabela vue_router
INSERT INTO vue_router (id_vue_router, path,                           name,                     component,                                        title,                          is_active, criado_em, alterado_em, criado_por_id, alterado_por_id)
VALUES                 (9,             '/classe_medicamento/edit',     'ClasseMedicamento-Page', 'cadastros/classe_medicamento-page/edit.vue',     'Classe Medicamento',           1,         NOW(),     NOW(),       1,             1              ),
                       (10,            '/classe_medicamento/index',    'ClasseMedicamento-List', 'cadastros/classe_medicamento-page/index.vue',    'Lista Classe Medicamentos',    1,         NOW(),     NOW(),       1,             1              ),
                       (11,            '/microorganismo/edit',         'Microorganismo-Page', 'cadastros/microorganismo-page/edit.vue',         'Microorganismos',              1,         NOW(),     NOW(),       1,             1              ),
                       (12,            '/microorganismo/index',        'Microorganismo-List', 'cadastros/microorganismo-page/index.vue',        'Lista Microorganismos',        1,         NOW(),     NOW(),       1,             1              ),
                       (13,            '/classe_microorganismo/edit',  'ClasseMicroorganismo-Page', 'cadastros/classe_microorganismo-page/edit.vue',  'Classe Microorganismos',       1,         NOW(),     NOW(),       1,             1              ),
                       (14,            '/classe_microorganismo/index', 'ClasseMicroorganismo-List', 'cadastros/classe_microorganismo-page/index.vue', 'Lista Classe Microorganismos', 1,         NOW(),     NOW(),       1,             1              );


-- Inserindo novos registros na tabela sub_menu
INSERT INTO sub_menu (id_menu, id_vue_router, title,                    is_active)
VALUES               (1,       10,            'Classe Medicamentos',    1        ),
                     (1,       12,            'Microorganismos',        1        ),
                     (1,       14,            'Classe Microorganismos', 1        );
