-- Criação da tabela de permissão
CREATE TABLE permissao_usuario
(
    id_permissao_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                 VARCHAR(255) NOT NULL,
    descricao            VARCHAR(255),
    is_active            TINYINT DEFAULT 1
);
