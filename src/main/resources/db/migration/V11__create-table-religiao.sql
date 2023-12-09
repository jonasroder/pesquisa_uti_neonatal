-- Criação da tabela de etnia
CREATE TABLE religiao
(
    id_religiao   INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    is_active TINYINT(1) DEFAULT 1
);


INSERT INTO religiao (descricao) VALUES ('Católico');
INSERT INTO religiao (descricao) VALUES ('Evangélico');
INSERT INTO religiao (descricao) VALUES ('Espírita');
INSERT INTO religiao (descricao) VALUES ('Budista');
INSERT INTO religiao (descricao) VALUES ('Judeu');
INSERT INTO religiao (descricao) VALUES ('Muçulmano');
INSERT INTO religiao (descricao) VALUES ('Candomblecista');
INSERT INTO religiao (descricao) VALUES ('Umbandista');
INSERT INTO religiao (descricao) VALUES ('Hindu');