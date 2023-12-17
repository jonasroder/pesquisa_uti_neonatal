-- Criação da tabela de Planos de Saúde
CREATE TABLE plano_saude
(
    id_plano_saude INT AUTO_INCREMENT PRIMARY KEY,
    descricao           VARCHAR(255) NOT NULL,
    is_active      TINYINT DEFAULT 1
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


