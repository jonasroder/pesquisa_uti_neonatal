-- Criação da tabela que liga usuários e permissões
CREATE TABLE tipo_usuario_permissao
(
    id_tipo_usuario_permissao BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_usuario           BIGINT       NOT NULL,
    end_point                 VARCHAR(255) NOT NULL,
    is_active                 TINYINT DEFAULT 1
);