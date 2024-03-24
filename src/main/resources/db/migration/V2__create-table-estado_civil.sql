CREATE TABLE estado_civil
(
    id_estado_civil BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO estado_civil (descricao, is_active)
VALUES ('Solteiro', 1);
INSERT INTO estado_civil (descricao, is_active)
VALUES ('Casado', 1);
INSERT INTO estado_civil (descricao, is_active)
VALUES ('Divorciado', 1);
