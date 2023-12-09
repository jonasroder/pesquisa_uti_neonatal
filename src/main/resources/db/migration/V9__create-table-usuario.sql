-- Criação da tabela de usuários
CREATE TABLE usuario
(
    id_usuario  INT AUTO_INCREMENT PRIMARY KEY,
    id_pessoa   INT,
    usuario     VARCHAR(255) NOT NULL,
    senha       VARCHAR(255) NOT NULL,
    id_situacao INT,
    is_active   TINYINT(1) DEFAULT 1
);



INSERT INTO usuario (id_pessoa, usuario, senha, id_situacao, is_active)
VALUES (1, 'jonasroder', '$2a$10$oBP684358Tg35ISnn9oP2O6N4mWjRhccWQ8IX9KlgX2cfLdThxO86', 1, 1);

INSERT INTO usuario (id_pessoa, usuario, senha, id_situacao, is_active)
VALUES (2, 'deniseroder', '$2a$10$dcq99u2wxKJ4T13HU79ZmuVzuVqzNHru3ivWppawXYH0jPY7YR1oG', 1, 1);
