CREATE TABLE sistema_fisiologico
(
    id_sistema_fisiologico BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo                 BIGINT UNIQUE NOT NULL,
    descricao              VARCHAR(255)  NOT NULL,
    criado_em              DATETIME,
    criado_por_id          BIGINT,
    alterado_em            DATETIME,
    alterado_por_id        BIGINT,
    is_active              TINYINT DEFAULT 1
);


INSERT INTO sistema_fisiologico (codigo, descricao,                criado_em, criado_por_id, is_active)
VALUES                          (0,      'Sistema respiratório',   NOW(),     1,             1        ),
                                (1,      'Sistema cardiovascular', NOW(),     1,             1        ),
                                (2,      'Sistema digestório',     NOW(),     1,             1        ),
                                (3,      'Sistema nervoso',        NOW(),     1,             1        ),
                                (4,      'Sistema geniturinário',  NOW(),     1,             1        ),
                                (5,      'Múltipla malformação',   NOW(),     1,             1        ),
                                (6,      'Baixo peso (<1500)',     NOW(),     1,             1        );
