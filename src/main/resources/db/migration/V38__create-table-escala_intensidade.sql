CREATE TABLE escala_intensidade
(
    id_intensidade  BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255),
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO escala_intensidade (descricao)
VALUES ('ausente'),
       ('Intensidade leve'),
       ('Intensidade média'),
       ('Intensidade forte'),
       ('Intensidade máxima');