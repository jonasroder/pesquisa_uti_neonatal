CREATE TABLE etnia
(
    id_etnia        BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO etnia (descricao, is_active)
VALUES ('Caucasiana', 1);
INSERT INTO etnia (descricao, is_active)
VALUES ('Afrodescendente', 1);
INSERT INTO etnia (descricao, is_active)
VALUES ('Indígena', 1);
INSERT INTO etnia (descricao, is_active)
VALUES ('Asiática', 1);
INSERT INTO etnia (descricao, is_active)
VALUES ('Latina', 1);
