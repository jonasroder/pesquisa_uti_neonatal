CREATE TABLE usuario_role
(
    id_usuario_role BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario      BIGINT NOT NULL,
    id_role         BIGINT NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
)
