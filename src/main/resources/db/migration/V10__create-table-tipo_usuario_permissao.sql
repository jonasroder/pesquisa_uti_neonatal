CREATE TABLE tipo_usuario_permissao
(
    id_tipo_usuario_permissao BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_usuario           BIGINT       NOT NULL,
    end_point                 VARCHAR(255) NOT NULL,
    criado_em                 DATETIME,
    criado_por_id             BIGINT,
    alterado_em               DATETIME,
    alterado_por_id           BIGINT,
    is_active                 TINYINT DEFAULT 1
);