CREATE TABLE options_q26_formulario_alimentar
(
    id_options_q26 BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao      VARCHAR(100) NOT NULL,
    is_active      TINYINT DEFAULT 1
);

INSERT INTO options_q26_formulario_alimentar (descricao)
VALUES ('1'),
       ('2'),
       ('3 ou mais');