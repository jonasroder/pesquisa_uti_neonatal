-- Criação da tabela de permissão
CREATE TABLE permissao
(
    id_permissao INT AUTO_INCREMENT PRIMARY KEY,
    nome         VARCHAR(255) NOT NULL,
    descricao    VARCHAR(255),
    is_active    TINYINT(1) DEFAULT 1
);
