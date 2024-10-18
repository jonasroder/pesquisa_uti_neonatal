CREATE TABLE via_administracao
(
    id_via_administracao BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo               BIGINT UNIQUE NOT NULL,
    descricao            VARCHAR(255)  NOT NULL,
    criado_em            DATETIME,
    criado_por_id        BIGINT,
    alterado_em          DATETIME,
    alterado_por_id      BIGINT,
    is_active            TINYINT DEFAULT 1
);


INSERT INTO via_administracao (codigo, descricao,       criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES                        (1,      'Oral',          NOW(),     1,             NOW(),       1,               1        ),
                              (2,      'Intravenosa',   NOW(),     1,             NOW(),       1,               1        ),
                              (3,      'Tópica',        NOW(),     1,             NOW(),       1,               1        ),
                              (4,      'Intramuscular', NOW(),     1,             NOW(),       1,               1        ),
                              (5,      'Subcutânea',    NOW(),     1,             NOW(),       1,               1        );
