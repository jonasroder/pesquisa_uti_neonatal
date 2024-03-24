CREATE TABLE periodo
(
    id_periodo      BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255),
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO periodo (descricao, is_active)
VALUES ('Por 1 dia', 1),
       ('Por 2 dias', 1),
       ('Por 3 dias', 1),
       ('Por 5 dias', 1),
       ('Por 7 dias', 1),
       ('Por 10 dias', 1),
       ('Por 14 dias', 1),
       ('Por 21 dias', 1),
       ('Por 1 mês', 1),
       ('Por 2 meses', 1),
       ('Por 3 meses', 1),
       ('Por 6 meses', 1),
       ('Por 1 ano', 1),
       ('Continuamente', 1),
       ('Até nova ordem', 1);
