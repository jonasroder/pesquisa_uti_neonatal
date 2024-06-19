CREATE TABLE sitio_malformacao
(
    id_sitio_malformacao BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo               BIGINT UNIQUE NOT NULL,
    descricao            VARCHAR(255)  NOT NULL,
    criado_em            DATETIME,
    criado_por_id        BIGINT,
    alterado_em          DATETIME,
    alterado_por_id      BIGINT,
    is_active            TINYINT DEFAULT 1
);


INSERT INTO sitio_malformacao (codigo, descricao,                criado_em,         alterado_em,       is_active)
VALUES                        (0,      'Sistema respiratório',   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (1,      'Sistema cardiovascular', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (2,      'Sistema digestório',     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (3,      'Sistema nervoso',        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (4,      'Sistema geniturinário',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                              (5,      'Outros',                 CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        );
