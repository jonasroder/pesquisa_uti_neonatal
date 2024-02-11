-- Criação da tabela de Tipos Pessoa
CREATE TABLE tipo_usuario
(
    id_tipo_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO tipo_usuario (descricao)
VALUES ('Admin');
INSERT INTO tipo_usuario (descricao)
VALUES ('Médico');
INSERT INTO tipo_usuario (descricao)
VALUES ('Estudante');
INSERT INTO tipo_usuario (descricao)
VALUES ('Pesquisador');