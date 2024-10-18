CREATE TABLE tipo_antimicrobiano
(
    id_tipo_antimicrobiano BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo                 BIGINT UNIQUE NOT NULL,
    descricao              VARCHAR(255)  NOT NULL,
    criado_em              DATETIME,
    criado_por_id          BIGINT,
    alterado_em            DATETIME,
    alterado_por_id        BIGINT,
    is_active              TINYINT DEFAULT 1
);


INSERT INTO tipo_antimicrobiano (codigo, descricao,     criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES                          (1,      'Antibiótico', NOW(),     1,             NOW(),       1,               1        ),
                                (2,      'Antifungico', NOW(),     1,             NOW(),       1,               1        );
