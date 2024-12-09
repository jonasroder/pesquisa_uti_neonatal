CREATE TABLE login_log
(
    id_login_log    BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario      BIGINT      NOT NULL,
    data_hora       DATETIME    NOT NULL,
    ip_address      VARCHAR(45) NOT NULL,
    status          VARCHAR(50) NOT NULL,
    motivo          VARCHAR(255),
    user_agent      VARCHAR(255),
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);
