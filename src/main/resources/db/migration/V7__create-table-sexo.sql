-- Criação da tabela de usuários
CREATE TABLE sexo
(
    id_sexo   INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    is_active TINYINT(1) DEFAULT 1
);



INSERT INTO sexo (descricao, is_active)
VALUES ('Masculino', 1);
INSERT INTO sexo (descricao, is_active)
VALUES ('Feminino', 1);
INSERT INTO sexo (descricao, is_active)
VALUES ('Não especificado', 1);
