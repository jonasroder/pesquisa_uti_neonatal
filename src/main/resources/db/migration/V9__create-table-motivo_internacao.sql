CREATE TABLE motivo_internacao
(
    id_motivo_internacao BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo               BIGINT       NOT NULL,
    descricao            VARCHAR(255) NOT NULL,
    criado_em            DATETIME,
    criado_por_id        BIGINT,
    alterado_em          DATETIME,
    alterado_por_id      BIGINT,
    is_active            TINYINT DEFAULT 1
);

INSERT INTO motivo_internacao (codigo, descricao,                    criado_em,         alterado_em,       is_active)
VALUES                        (0,      'Anóxia',                     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (0,      'Apneia',                     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (0,      'SDR/Síndrome de Desconforto Respiratório',
                                                                     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (0,      'SMH/Síndrome da Membrana Hialina',
                                                                     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (1,      'Cardiopatia Congênita',      CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      'Atresia de Esôfago',         CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      'Atresia Intestinal',         CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      'Ânus Imperfurado',           CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      'Enterocolite Necrotizante',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      'Fístula Anal',               CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      'Gastrosquise',               CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      'Onfalocele',                 CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (3,      'Convulsão',                  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (3,      'Meningite',                  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (3,      'Microcefalia',               CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (3,      'Mielomeningocele',           CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (4,      'Hidrocele',                  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (4,      'Infecção do Trato Urinário', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (6,      'Baixo Peso (<1500)',         CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        );