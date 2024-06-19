CREATE TABLE genero
(
    id_genero       BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo          BIGINT       NOT NULL,
    descricao       VARCHAR(255) NULL,
    criado_em       DATETIME     NULL,
    criado_por_id   BIGINT       NULL,
    alterado_em     DATETIME     NULL,
    alterado_por_id BIGINT       NULL,
    is_active       TINYINT      NULL
);


-- Inserindo dados na tabela genero
INSERT INTO genero (codigo, descricao,          criado_em,             criado_por_id, alterado_em,           alterado_por_id, is_active)
VALUES             (1,      'Escherichia',      '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (2,      'Klebsiella',       '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (3,      'Serratia',         '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (4,      'Enterobacter',     '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (5,      'Pseudomonas',      '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (6,      'Acinetobacter',    '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (7,      'Sphingomonas',     '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (8,      'Haemophilus',      '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (9,      'Stenotrophomonas', '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (10,     'Elizabethkingia',  '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (11,     'Burkholderia',     '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (12,     'Cronobacter',      '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (13,     'Pantoea',          '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (14,     'Enterobacter',     '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (15,     'Proteus',          '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (16,     'Morganella',       '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (17,     'Staphylococcus',   '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (18,     'Streptococcus',    '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (19,     'Enterococcus',     '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (20,     'Cocos',            '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (21,     'Bastonetes',       '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (22,     'Leuconostoc',      '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (23,     'Listeria',         '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (24,     'Micrococcus',      '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (25,     'Candida',          '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                   (26,     'Trichosporon',     '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        );
