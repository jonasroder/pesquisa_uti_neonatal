CREATE TABLE permissao_usuario
(
    id_permissao_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                 VARCHAR(255) NOT NULL,
    descricao            VARCHAR(255),
    criado_em            DATETIME,
    criado_por_id        BIGINT,
    alterado_em          DATETIME,
    alterado_por_id      BIGINT,
    is_active            TINYINT DEFAULT 1
);
