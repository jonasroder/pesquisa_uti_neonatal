CREATE TABLE options_q31_formulario_alimentar
(
    id_options_q31 BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao      VARCHAR(100) NOT NULL,
    is_active      TINYINT DEFAULT 1
);

INSERT INTO options_q31_formulario_alimentar (descricao)
VALUES ('1'),
       ('2'),
       ('3'),
       ('4'),
       ('5'),
       ('6 ou +'),
       ('Não sabe');
