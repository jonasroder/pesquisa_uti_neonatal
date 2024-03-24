CREATE TABLE frequencia_dificuldades
(
    id_frequencia_dificuldades TINYINT AUTO_INCREMENT PRIMARY KEY,
    descricao                  VARCHAR(100) NOT NULL,
    criado_em                  DATETIME,
    criado_por_id              BIGINT,
    alterado_em                DATETIME,
    alterado_por_id            BIGINT,
    is_active                  TINYINT DEFAULT 1
);

INSERT INTO frequencia_dificuldades (descricao)
VALUES ('Nenhuma no último mês'),
       ('Menos de 1 vez/semana'),
       ('1 ou 2 vezes/semana'),
       ('3 ou mais vezes/semana');

