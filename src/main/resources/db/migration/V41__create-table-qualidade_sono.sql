CREATE TABLE qualidade_sono
(
    id_qualidade_sono TINYINT AUTO_INCREMENT PRIMARY KEY,
    descricao         VARCHAR(50) NOT NULL,
    criado_em         DATETIME,
    criado_por_id     BIGINT,
    alterado_em       DATETIME,
    alterado_por_id   BIGINT,
    is_active         TINYINT DEFAULT 1
);

INSERT INTO qualidade_sono (descricao)
VALUES ('Muito boa'),
       ('Boa'),
       ('Ruim'),
       ('Muito ruim');
