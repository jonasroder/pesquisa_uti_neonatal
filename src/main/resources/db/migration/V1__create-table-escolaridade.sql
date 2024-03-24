CREATE TABLE escolaridade
(
    id_escolaridade BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO escolaridade (descricao, is_active)
VALUES ('Fundamental', 1);
INSERT INTO escolaridade (descricao, is_active)
VALUES ('Médio', 1);
INSERT INTO escolaridade (descricao, is_active)
VALUES ('Superior', 1);
INSERT INTO escolaridade (descricao, is_active)
VALUES ('Pós-Graduação', 1);
