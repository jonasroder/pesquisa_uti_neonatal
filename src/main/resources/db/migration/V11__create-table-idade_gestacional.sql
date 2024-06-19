CREATE TABLE idade_gestacional
(
    id_idade_gestacional BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo               BIGINT UNIQUE NOT NULL,
    descricao            VARCHAR(255)  NOT NULL,
    criado_em            DATETIME,
    criado_por_id        BIGINT,
    alterado_em          DATETIME,
    alterado_por_id      BIGINT,
    is_active            TINYINT DEFAULT 1
);

INSERT INTO idade_gestacional (codigo, descricao,                  criado_em,         alterado_em,       is_active)
VALUES                        (0,      '≤ 28 semanas',             CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (1,      '28 a menos de 32 semanas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      '32 a menos de 37 semanas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (3,      '≥ 37 semanas',             CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (5,      'Não informado',            CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        );

