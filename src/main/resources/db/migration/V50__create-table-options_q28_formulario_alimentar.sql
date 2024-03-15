CREATE TABLE options_q28_formulario_alimentar
(
    id_options_q28 BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao      VARCHAR(100) NOT NULL,
    is_active      TINYINT DEFAULT 1
);

INSERT INTO options_q28_formulario_alimentar (descricao)
VALUES ('1 vez no dia'),
       ('2 vezes no dia'),
       ('3 ou mais vezes no dia');