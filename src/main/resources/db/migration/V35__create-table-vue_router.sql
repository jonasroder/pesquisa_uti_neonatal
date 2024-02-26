CREATE TABLE vue_router
(
    id_vue_router BIGINT AUTO_INCREMENT PRIMARY KEY,
    path          VARCHAR(250),
    name          VARCHAR(250),
    component     VARCHAR(250),
    title         VARCHAR(250),
    is_active     TINYINT DEFAULT 1
);


INSERT INTO vue_router (path, name, component, title)
VALUES ('/', 'Home', "@/views/main-page/main-page.vue", 'Jonas e Denise'),
       ('/login', 'Login-Page', "@/views/login/index.vue", 'Saúde em Equilíbrio - Login'),
       ('/paciente/edit', 'Paciente-Page', "@/views/paciente-page/edit.vue", 'Cadastrar Paciente'),
       ('/paciente/index', 'Paciente-List', "@/views/paciente-page/index.vue", 'Lista Pacientes'),
       ('/consulta/edit', 'Consulta-Page', "@/views/consulta-page/edit.vue", 'Consulta');
