CREATE TABLE arquivo_consulta
(
    id_arquivo      BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_arquivo BIGINT,
    id_consulta     BIGINT,
    nome_arquivo    VARCHAR(255) NOT NULL,
    caminho_arquivo VARCHAR(255) NOT NULL,
    tamanho         BIGINT,
    formato         VARCHAR(255),
    data_upload     DATETIME DEFAULT CURRENT_TIMESTAMP,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT  DEFAULT 1
);
