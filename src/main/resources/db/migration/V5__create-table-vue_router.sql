CREATE TABLE vue_router
(
    id_vue_router   BIGINT AUTO_INCREMENT PRIMARY KEY,
    path            VARCHAR(250),
    name            VARCHAR(250),
    component       VARCHAR(250),
    title           VARCHAR(250),
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO vue_router (path, name, component, title, is_active, criado_em, alterado_em, criado_por_id, alterado_por_id)
VALUES ('/', 'Neonato-List', 'neonato-page/index.vue', 'Lista Neonatos', 1, NOW(), NOW(), 1, 1),
       ('/login', 'Login-Page', 'login/index.vue', 'Saúde em Equilíbrio - Login', 1, NOW(), NOW(), 1, 1),
       ('/neonato/edit', 'Neonato-Page', 'neonato-page/edit.vue', 'Cadastrar Neonato', 1, NOW(), NOW(), 1, 1),
       ('/prontuario/edit', 'Prontuario-Page', 'prontuario-page/edit.vue', 'Prontuário', 1, NOW(), NOW(), 1, 1);
