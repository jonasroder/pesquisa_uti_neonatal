CREATE TABLE microorganismo
(
    id_microorganismo               BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo                          BIGINT       NOT NULL,
    id_genero                       BIGINT       NULL,
    id_classificacao_microorganismo BIGINT       NULL,
    especie                         VARCHAR(255) NULL,
    acronimo                        VARCHAR(255) NULL,
    criado_em                       DATETIME     NULL,
    criado_por_id                   BIGINT       NULL,
    alterado_em                     DATETIME     NULL,
    alterado_por_id                 BIGINT       NULL,
    is_active                       TINYINT      NULL
);


-- Inserindo dados na tabela microorganismo
INSERT INTO microorganismo (codigo, id_genero, id_classificacao_microorganismo, especie,             acronimo,                       criado_em,             criado_por_id, alterado_em,           alterado_por_id, is_active)
VALUES                     (0,      1,         1,                               'coli',              'E. coli',                      '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (1,      2,         1,                               'pneumoniae',        'K. pneumoniae',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (2,      17,        0,                               'lugdunensis',       'S. lugdunensis',               '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (3,      3,         1,                               'marcescens',        'S. marcescens',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (4,      17,        0,                               'epidermidis',       'S. epidermidis',               '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (5,      17,        0,                               'haemolyticus',      'S. haemolyticus',              '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (6,      17,        0,                               'hominis',           'S. hominis',                   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (7,      18,        0,                               'agalactiae',        'S. agalactiae',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (8,      17,        0,                               'aureus',            'S. aureus',                    '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (9,      19,        0,                               'faecalis',          'E. faecalis',                  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (10,     4,         1,                               'cloacae',           'E. cloacae',                   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (11,     5,         1,                               'aeruginosa',        'P. aeruginosa',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (12,     17,        0,                               'warneri',           'S. warneri',                   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (13,     6,         1,                               'baumannii',         'A. baumannii',                 '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (14,     17,        0,                               'capitis',           'S. capitis',                   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (15,     25,        2,                               'albicans',          'C. albicans',                  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (16,     25,        2,                               'parapsilosis',      'C. parapsilosis',              '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (17,     25,        2,                               'gilliermondii',     'C. gilliermondii',             '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (18,     20,        0,                               'gram positivos',    'C. gram positivos',            '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (19,     18,        0,                               'spp.',              'Estreptococcus spp.',          '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (20,     7,         1,                               'paucimobilis',      'S. paucimobilis',              '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (21,     5,         1,                               'spp.',              'Pseudomonas spp.',             '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (22,     8,         1,                               'spp.',              'Haemophylus spp.',             '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (23,     21,        0,                               'Gram positivos',    'Bastonetes Gram positivos',    '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (24,     2,         1,                               'oxytoca',           'K. oxytoca',                   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (25,     2,         1,                               'aerogenes',         'K. aerogenes',                 '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (27,     25,        2,                               'glabrata',          'C. glabrata',                  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (28,     9,         1,                               'maltophilia',       'S. maltophilia',               '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (29,     21,        1,                               'Gram negativos',    'Bastonestes Gram negativos',   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (30,     5,         1,                               'putida',            'P. putida',                    '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (31,     17,        0,                               'xylosus',           'S. xylosus',                   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (32,     18,        0,                               'viridans',          'S. viridans',                  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (33,     10,        1,                               'meningoseptica',    'E. meningoseptica',            '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (34,     11,        1,                               'cepacia',           'B. cepacia',                   '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (35,     12,        1,                               'sakazakii',         'C. sakazakii',                 '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (36,     26,        2,                               'spp.',              'Trichosporon spp.',            '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (37,     17,        0,                               'carnosus',          'S. carnosus',                  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (38,     17,        0,                               'coagulase negativa (SCON)',
                                                                                                     'S. coagulase negativa (SCON)', '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (39,     18,        0,                               'pneumoniae',        'S. pneumoniae',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (40,     17,        0,                               'intermedius',       'S. intermedius',               '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (41,     13,        1,                               'spp.',              'Pantoea spp.',                 '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (42,     17,        0,                               'pseudointermedius', 'S. pseudointermedius',         '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (43,     4,         1,                               'asburiae',          'E. asburiae',                  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (44,     25,        2,                               'tropicalis',        'C. tropicalis',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (45,     15,        1,                               'mirabilis',         'P. mirabilis',                 '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (46,     22,        0,                               'mesenteroides',     'L. mesenteroides',             '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (47,     3,         1,                               'spp.',              'Serratia spp.',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (48,     16,        1,                               'morganii',          'M. morganii',                  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (49,     6,         1,                               'ursingii',          'A. ursingii',                  '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (50,     18,        0,                               'parasanguinis',     'S. parasanguinis',             '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (51,     5,         1,                               'putida',            'P. putida',                    '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (52,     23,        0,                               'spp.',              'Listeria spp.',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (53,     25,        2,                               'lusitaniae',        'C. lusitaniae',                '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (54,     7,         1,                               'paucimobilis',      'S. paucimobilis',              '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        ),
                           (55,     24,        0,                               'spp.',              'Micrococcus spp.',             '2024-06-01 13:53:51', 1,             '2024-06-01 13:53:51', 1,               1        );
