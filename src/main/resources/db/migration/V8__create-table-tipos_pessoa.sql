-- Criação da tabela de Tipos Pessoa
CREATE TABLE tipos_pessoa
(
    id_tipo_pessoa INT AUTO_INCREMENT PRIMARY KEY,
    descricao      VARCHAR(255) NOT NULL,
    is_active      TINYINT(1) DEFAULT 1
);


INSERT INTO tipos_pessoa (descricao)
VALUES ('Paciente');
INSERT INTO tipos_pessoa (descricao)
VALUES ('Fornecedor');
INSERT INTO tipos_pessoa (descricao)
VALUES ('Estudante');
INSERT INTO tipos_pessoa (descricao)
VALUES ('Pesquisador');