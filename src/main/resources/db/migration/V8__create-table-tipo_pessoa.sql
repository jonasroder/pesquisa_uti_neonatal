-- Criação da tabela de Tipos Pessoa
CREATE TABLE tipo_pessoa
(
    id_tipo_pessoa INT AUTO_INCREMENT PRIMARY KEY,
    descricao      VARCHAR(255) NOT NULL,
    is_active      TINYINT(1) DEFAULT 1
);


INSERT INTO tipo_pessoa (descricao)
VALUES ('Paciente');
INSERT INTO tipo_pessoa (descricao)
VALUES ('Fornecedor');
INSERT INTO tipo_pessoa (descricao)
VALUES ('Estudante');
INSERT INTO tipo_pessoa (descricao)
VALUES ('Pesquisador');