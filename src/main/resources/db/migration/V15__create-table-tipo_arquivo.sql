-- Criação da tabela de tipo_arquivo
CREATE TABLE tipo_arquivo
(
    id_tipo_arquivo  INT AUTO_INCREMENT PRIMARY KEY,
    descricao    VARCHAR(255) NOT NULL,
    is_active    TINYINT(1) DEFAULT 1
);

