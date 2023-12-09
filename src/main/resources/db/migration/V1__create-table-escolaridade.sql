-- Criação da tabela de escolaridade
CREATE TABLE escolaridade
(
    id_escolaridade INT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    is_active       TINYINT(1) DEFAULT 1
);


INSERT INTO escolaridade (descricao, is_active)
VALUES ('Fundamental', 1);
INSERT INTO escolaridade (descricao, is_active)
VALUES ('Médio', 1);
INSERT INTO escolaridade (descricao, is_active)
VALUES ('Superior', 1);
INSERT INTO escolaridade (descricao, is_active)
VALUES ('Pós-Graduação', 1);
