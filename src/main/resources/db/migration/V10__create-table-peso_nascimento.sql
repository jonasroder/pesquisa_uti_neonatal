CREATE TABLE peso_nascimento
(
    id_peso_nascimento BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo             BIGINT UNIQUE NOT NULL,
    descricao          VARCHAR(255)  NOT NULL,
    criado_em          DATETIME,
    criado_por_id      BIGINT,
    alterado_em        DATETIME,
    alterado_por_id    BIGINT,
    is_active          TINYINT DEFAULT 1
);

INSERT INTO peso_nascimento (codigo, descricao, criado_em, alterado_em, is_active)
VALUES (0, 'Extremo baixo peso < 1000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       (1, 'Muito baixo peso < 1500', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       (2, 'Baixo peso < 2500', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       (3, 'Peso > 2500', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       (4, 'Muito extremo baixo peso ≤ 750', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
