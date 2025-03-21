
-- Inserindo novos registros na tabela vue_router
INSERT INTO vue_router (id_vue_router, path,                           name,                        component,                                        title,                          is_active, criado_em, alterado_em, criado_por_id, alterado_por_id)
VALUES                 (20,            '/causa_obito/edit',  'CausaObito-Page', 'cadastros/causa_obito-page/edit.vue',  'Causa do Óbito',       1,         NOW(),     NOW(),       1,             1              ),
                       (21,            '/causa_obito/index', 'CausaObito-List', 'cadastros/causa_obito-page/index.vue', 'Lista Causa do Óbito', 1,         NOW(),     NOW(),       1,             1              ),
                       (22,            '/idade_gestacional/edit',  'IdadeGestacional-Page', 'cadastros/idade_gestacional-page/edit.vue',  'Idade Gestacional',       1,         NOW(),     NOW(),       1,             1              ),
                       (23,            '/idade_gestacional/index', 'IdadeGestacional-List', 'cadastros/idade_gestacional-page/index.vue', 'Lista Idade Gestacional', 1,         NOW(),     NOW(),       1,             1              ),
                       (24,            '/local_nascimento/edit',  'LocalNascimento-Page', 'cadastros/local_nascimento-page/edit.vue',  'Local do Nascimento',       1,         NOW(),     NOW(),       1,             1              ),
                       (25,            '/local_nascimento/index', 'LocalNascimento-List', 'cadastros/local_nascimento-page/index.vue', 'Lista Local do Nascimento', 1,         NOW(),     NOW(),       1,             1              ),
                       (26,            '/mecanismo_resistencia_microorganismo/edit',  'MecanismoResistenciaMicroorganismo-Page', 'cadastros/mecanismo_resistencia_microorganismo-page/edit.vue',  'Mecanismo de Resistência',       1,         NOW(),     NOW(),       1,             1              ),
                       (27,            '/mecanismo_resistencia_microorganismo/index', 'MecanismoResistenciaMicroorganismo-List', 'cadastros/mecanismo_resistencia_microorganismo-page/index.vue', 'Lista Mecanismo de Resistência', 1,         NOW(),     NOW(),       1,             1              ),
                       (28,            '/perfil_resistencia_microorganismo/edit',  'PerfilResistenciaMicroorganismo-Page', 'cadastros/perfil_resistencia_microorganismo-page/edit.vue',  'Perfil de Resistência',       1,         NOW(),     NOW(),       1,             1              ),
                       (29,            '/perfil_resistencia_microorganismo/index', 'PerfilResistenciaMicroorganismo-List', 'cadastros/perfil_resistencia_microorganismo-page/index.vue', 'Lista Perfil de Resistência', 1,         NOW(),     NOW(),       1,             1              ),
                       (30,            '/peso_nascimento/edit',  'PesoNascimento-Page', 'cadastros/peso_nascimento-page/edit.vue',  'Classificação de Peso',       1,         NOW(),     NOW(),       1,             1              ),
                       (31,            '/peso_nascimento/index', 'PesoNascimento-List', 'cadastros/peso_nascimento-page/index.vue', 'Lista Classificação de Peso', 1,         NOW(),     NOW(),       1,             1              ),
                       (32,            '/rotura_membrana/edit',  'RoturaMembrana-Page', 'cadastros/rotura_membrana-page/edit.vue',  'Bolsa Rota',       1,         NOW(),     NOW(),       1,             1              ),
                       (33,            '/rotura_membrana/index', 'RoturaMembrana-List', 'cadastros/rotura_membrana-page/index.vue', 'Lista Bolsa Rota', 1,         NOW(),     NOW(),       1,             1              ),
                       (34,            '/sexo/edit',  'Sexo-Page', 'cadastros/sexo-page/edit.vue',  'Sexo',       1,         NOW(),     NOW(),       1,             1              ),
                       (35,            '/sexo/index', 'Sexo-List', 'cadastros/sexo-page/index.vue', 'Lista Sexo', 1,         NOW(),     NOW(),       1,             1              ),
                       (36,            '/sitio_cirurgia/edit',  'SitioCirurgia-Page', 'cadastros/sitio_cirurgia-page/edit.vue',  'Sítio Cirurgia',       1,         NOW(),     NOW(),       1,             1              ),
                       (37,            '/sitio_cirurgia/index', 'SitioCirurgia-List', 'cadastros/sitio_cirurgia-page/index.vue', 'Lista Sítio Cirurgia', 1,         NOW(),     NOW(),       1,             1              ),
                       (38,            '/sitio_malformacao/edit',  'SitioMalformacao-Page', 'cadastros/sitio_malformacao-page/edit.vue',  'Sítio Malformação',       1,         NOW(),     NOW(),       1,             1              ),
                       (39,            '/sitio_malformacao/index', 'SitioMalformacao-List', 'cadastros/sitio_malformacao-page/index.vue', 'Lista Sítio Malformação', 1,         NOW(),     NOW(),       1,             1              ),
                       (40,            '/tipo_parto/edit',  'TipoParto-Page', 'cadastros/tipo_parto-page/edit.vue',  'Tipo Parto',       1,         NOW(),     NOW(),       1,             1              ),
                       (41,            '/tipo_parto/index', 'TipoParto-List', 'cadastros/tipo_parto-page/index.vue', 'Lista Tipo Parto', 1,         NOW(),     NOW(),       1,             1              ),
                       (42,            '/via_administracao/edit',  'ViaAdministracao-Page', 'cadastros/via_administracao-page/edit.vue',  'Via de Administração',       1,         NOW(),     NOW(),       1,             1              ),
                       (43,            '/via_administracao/index', 'ViaAdministracao-List', 'cadastros/via_administracao-page/index.vue', 'Lista Via de Administração', 1,         NOW(),     NOW(),       1,             1              );





-- Inserindo novos registros na tabela sub_menu
INSERT INTO sub_menu (id_menu, id_vue_router, title,                    is_active)
VALUES               (1,       21,            'Causa do Óbito',    1        ),
               (1,       23,            'Idade Gestacional',    1        ),
               (1,       25,            'Local do Nascimento',    1        ),
               (1,       27,            'Mecanismo de Resistência',    1        ),
               (1,       29,            'Perfil de Resistência',    1        ),
               (1,       31,            'Classificação de Peso',    1        ),
               (1,       33,            'Bolsa Rota',    1        ),
               (1,       35,            'Sexo',    1        ),
               (1,       37,            'Sítio Cirurgia',    1        ),
               (1,       39,            'Sítio Malformação',    1        ),
               (1,       41,            'Tipo Parto',    1        ),
               (1,       43,            'Via de Administração',    1        );
