CREATE TABLE usuario
(
    id_usuario      BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario         VARCHAR(255) NOT NULL,
    senha           VARCHAR(255) NOT NULL,
    nome_completo   VARCHAR(255) NOT NULL,
    titulo          VARCHAR(10),
    especialidade   VARCHAR(255),
    telefone_1      VARCHAR(20),
    telefone_2      VARCHAR(20),
    cpf             VARCHAR(11)  NOT NULL UNIQUE,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO usuario (usuario, senha, nome_completo, titulo, especialidade, telefone_1, telefone_2, cpf, criado_em,
                     criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES ('jonasroder', '$2a$10$oBP684358Tg35ISnn9oP2O6N4mWjRhccWQ8IX9KlgX2cfLdThxO86', 'Jonas Roder', 'ADM',
        'Programador', NULL, NULL, '00000000000', NULL, NULL, NULL, NULL, 1);
INSERT INTO usuario (usuario, senha, nome_completo, titulo, especialidade, telefone_1, telefone_2, cpf, criado_em,
                     criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES ('deniseroder', '$2a$10$dcq99u2wxKJ4T13HU79ZmuVzuVqzNHru3ivWppawXYH0jPY7YR1oG', 'Denise Roder', 'Dra',
        'Pesquisador', NULL, NULL, '11111111111', NULL, NULL, NULL, NULL, 1);