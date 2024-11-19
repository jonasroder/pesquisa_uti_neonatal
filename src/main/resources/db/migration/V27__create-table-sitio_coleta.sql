CREATE TABLE sitio_coleta
(
    id_sitio_coleta BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo          BIGINT       NOT NULL,
    descricao       VARCHAR(255) NULL,
    criado_em       DATETIME     NULL,
    criado_por_id   BIGINT       NULL,
    alterado_em     DATETIME     NULL,
    alterado_por_id BIGINT       NULL,
    is_active       BIT          NULL
);

-- Inserindo dados na tabela sitio_coleta
INSERT INTO sitio_coleta (codigo, descricao,           criado_em,             criado_por_id, alterado_em,           alterado_por_id, is_active)
VALUES                   (0,      'SANGUE',            '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                         (1,      'LCR',               '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                         (2,      'SECREÇÃO OCULAR',   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                         (3,      'SWAB ANAL',         '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                         (4,      'PONTA DE CATETER',  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                         (5,      'URINA',             '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                         (6,      'SECREÇÃO PULMONAR', '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                         (7,      'LÍQUIDO ASCÍTICO',  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                         (8,      'LÍQUIDO PLEURAL',   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        );
