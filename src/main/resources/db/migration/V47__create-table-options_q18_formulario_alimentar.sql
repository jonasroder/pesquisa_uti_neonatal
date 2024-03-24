CREATE TABLE options_q18_formulario_alimentar
(
    id_options_q18  BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(100) NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO options_q18_formulario_alimentar (descricao)
VALUES ('No almoço (1 vez ao dia)'),
       ('No almoço e no jantar (2 vezes ao dia)'),
       ('Num dia comum (s/ salada)'),
       ('Nunca (pule para Q19)'),
       ('Quase nunca (pule para Q19)'),
       ('Nunca (pule para Q19)');