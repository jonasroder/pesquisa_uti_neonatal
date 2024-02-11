CREATE TABLE usuario
(
    id_usuario      BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_usuario BIGINT,
    usuario         VARCHAR(255) NOT NULL,
    senha           VARCHAR(255) NOT NULL,
    nome_completo   VARCHAR(255) NOT NULL,
    telefone_1      VARCHAR(20),
    telefone_2      VARCHAR(20),
    cpf             VARCHAR(11)  NOT NULL UNIQUE,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO usuario (id_tipo_usuario, usuario, senha, is_active, nome_completo, telefone_1, telefone_2, cpf)
VALUES (1, 'jonasroder', '$2a$10$oBP684358Tg35ISnn9oP2O6N4mWjRhccWQ8IX9KlgX2cfLdThxO86', 1, 'Jonas Roder', null, null,
        '00000000000');

INSERT INTO usuario (id_tipo_usuario, usuario, senha, is_active, nome_completo, telefone_1, telefone_2, cpf)
VALUES (1, 'deniseroder', '$2a$10$dcq99u2wxKJ4T13HU79ZmuVzuVqzNHru3ivWppawXYH0jPY7YR1oG', 1, 'Denise Roder', null, null,
        '11111111111');
