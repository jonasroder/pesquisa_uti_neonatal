-- Criação da tabela de etnia
CREATE TABLE etnia
(
    id_etnia  INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    is_active TINYINT(1) DEFAULT 1
);


INSERT INTO etnia (descricao, is_active)
VALUES ('Caucasiana', 1);
INSERT INTO etnia (descricao, is_active)
VALUES ('Afrodescendente', 1);
INSERT INTO etnia (descricao, is_active)
VALUES ('Indígena', 1);
INSERT INTO etnia (descricao, is_active)
VALUES ('Asiática', 1);
INSERT INTO etnia (descricao, is_active)
VALUES ('Latina', 1);
