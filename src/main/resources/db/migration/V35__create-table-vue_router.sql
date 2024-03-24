CREATE TABLE vue_router
(
    id_vue_router BIGINT AUTO_INCREMENT PRIMARY KEY,
    path          VARCHAR(250),
    name          VARCHAR(250),
    component     VARCHAR(250),
    title         VARCHAR(250),
    is_active     TINYINT DEFAULT 1
);


INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/', 'Home', 'main-page/main-page.vue', 'Home', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/login', 'Login-Page', 'login/index.vue', 'Saúde em Equilíbrio - Login', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/paciente/edit', 'Paciente-Page', 'paciente-page/edit.vue', 'Cadastrar Paciente', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/paciente/index', 'Paciente-List', 'paciente-page/index.vue', 'Lista Pacientes', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/consulta/edit', 'Consulta-Page', 'consulta-page/edit.vue', 'Consulta', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/medicamento/index', 'Medicamento-List', 'cadastros/medicamento-page/index.vue', 'Lista Medicamento', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/suplemento/edit', 'Suplemento-Page', 'cadastros/suplemento-page/edit.vue', 'Suplementos', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/diagnostico/edit', 'Diagnostico-Page', 'cadastros/diagnostico-page/edit.vue', 'Diagnóstico', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/informacao_saude/edit', 'InformacaoSaude-Page', 'cadastros/informacao_saude-page/edit.vue', 'Informações Saúde', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/medicamento/edit', 'Medicamento-Page', 'cadastros/medicamento-page/edit.vue', 'Medicamento', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/suplemento/index', 'Suplemento-List', 'cadastros/suplemento-page/index.vue', 'Lista Suplementos', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/ingrediente/index', 'Ingrediente-List', 'cadastros/ingrediente-page/index.vue', 'Lista Ingredientes', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/ingrediente/edit', 'Ingrediente-Page', 'cadastros/ingrediente-page/edit.vue', 'Ingredientes', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/diagnostico/index', 'Diagnostico-List', 'cadastros/diagnostico-page/index.vue', 'Lista Diagnósticos', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/informacao_saude/index', 'InformacaoSaude-List', 'cadastros/informacao_saude-page/index.vue', 'Lista Informações Saúde', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/formulario_hamilton/edit', 'FormularioHamilton-Page', 'formulario_hamilton-page/edit.vue', 'Formulario Hamilton', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/formulario_pittsburgh/edit', 'FormularioPittsburgh-Page', 'formulario_pittsburgh-page/edit.vue', 'Formulario Pittsburgh', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/formulario_atividade_fisica/edit', 'FormularioAtividadeFisica-Page', 'formulario_atividade_fisica-page/edit.vue', 'Formulario Atividade Física', 1);
INSERT INTO vue_router (`path`, name, component, title, is_active) VALUES('/formulario_alimentar/edit', 'FormularioAlimentar-Page', 'formulario_alimentar-page/edit.vue', 'Formulário Alimentar', NULL);