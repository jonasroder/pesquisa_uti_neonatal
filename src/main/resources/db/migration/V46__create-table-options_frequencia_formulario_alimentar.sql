CREATE TABLE options_frequencia_formulario_alimentar
(
    id_options_frequencia_formulario_alimentar BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao                                  VARCHAR(100) NOT NULL,
    is_active                                  TINYINT DEFAULT 1
);

INSERT INTO options_frequencia_formulario_alimentar (descricao)
VALUES ('1 a 2 dias por semana'),
       ('3 a 4 dias por semana'),
       ('5 a 6 dias por semana'),
       ('Todos os dias (inclusive sábado e domingo)'),
       ('Quase nunca'),
       ('Nunca');

