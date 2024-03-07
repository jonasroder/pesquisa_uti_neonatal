CREATE TABLE qualidade_sono
(
    id_qualidade_sono TINYINT AUTO_INCREMENT PRIMARY KEY,
    descricao         VARCHAR(50) NOT NULL,
    is_active         TINYINT DEFAULT 1 -- Para ativar/desativar opções
);

INSERT INTO qualidade_sono (descricao)
VALUES ('Muito boa'),
       ('Boa'),
       ('Ruim'),
       ('Muito ruim');
