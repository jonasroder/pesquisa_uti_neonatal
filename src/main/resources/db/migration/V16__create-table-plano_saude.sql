CREATE TABLE plano_saude
(
    id_plano_saude  BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO plano_saude (descricao)
VALUES ('UNIMED'),
       ('Bradesco Saúde Efetivo'),
       ('Bradesco Saúde Nacional'),
       ('NotreDame Intermédica Advance 600'),
       ('NotreDame Intermédica Premium 900'),
       ('Hapvida Nosso Plano – Rede Própria'),
       ('Hapvida Pleno – Rede Credenciada'),
       ('Amil Fácil'),
       ('Amil One S6500 Black'),
       ('SulAmérica Saúde');


