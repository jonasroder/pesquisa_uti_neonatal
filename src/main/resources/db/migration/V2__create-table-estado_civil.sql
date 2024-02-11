CREATE TABLE estado_civil
(
    id_estado_civil BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO estado_civil (descricao, is_active)
VALUES ('Solteiro', 1);
INSERT INTO estado_civil (descricao, is_active)
VALUES ('Casado', 1);
INSERT INTO estado_civil (descricao, is_active)
VALUES ('Divorciado', 1);
