-- Criação da tabela de arquivos
CREATE TABLE arquivo
(
    id_arquivo      INT AUTO_INCREMENT PRIMARY KEY,
    entidade        VARCHAR(255) NOT NULL,
    id_entidade     INT          NOT NULL,
    id_tipo_arquivo INT,
    nome_arquivo    VARCHAR(255) NOT NULL,
    caminho_arquivo VARCHAR(255) NOT NULL,
    tamanho         BIGINT,
    formato         VARCHAR(50),
    data_upload     DATETIME DEFAULT CURRENT_TIMESTAMP,
    metadados       JSON,
    is_active       TINYINT(1) DEFAULT 1,
    INDEX (entidade, id_entidade)
);
