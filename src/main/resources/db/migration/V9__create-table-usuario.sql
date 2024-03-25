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

INSERT INTO usuario (usuario, senha, is_active, nome_completo, telefone_1, telefone_2, cpf)
VALUES ('jonasroder', '$2a$10$oBP684358Tg35ISnn9oP2O6N4mWjRhccWQ8IX9KlgX2cfLdThxO86', 1, 'Jonas Roder', null, null,
        '00000000000');

INSERT INTO usuario (usuario, senha, is_active, nome_completo, telefone_1, telefone_2, cpf)
VALUES ('deniseroder', '$2a$10$dcq99u2wxKJ4T13HU79ZmuVzuVqzNHru3ivWppawXYH0jPY7YR1oG', 1, 'Denise Roder', null, null,
        '11111111111');
