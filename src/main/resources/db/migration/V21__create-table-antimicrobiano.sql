CREATE TABLE antimicrobiano
(
    id_antimicrobiano        BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_classe_antimicrobiano BIGINT,
    id_via_administracao     BIGINT,
    codigo                   BIGINT UNIQUE NOT NULL,
    descricao                VARCHAR(255)  NOT NULL,
    criado_em                DATETIME,
    criado_por_id            BIGINT,
    alterado_em              DATETIME,
    alterado_por_id          BIGINT,
    is_active                TINYINT DEFAULT 1
);

INSERT INTO antimicrobiano (id_classe_antimicrobiano, id_via_administracao, codigo, descricao,                       criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES                     (1,                        2,                    1,      'Amicacina',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (1,                        2,                    2,      'Gentamicina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (2,                        1,                    3,      'Rifampicina',                   NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (3,                        1,                    4,      'Ampicilina',                    NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (3,                        2,                    5,      'Ampicilina-sulbactam',          NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        1,                    6,      'Amoxicilina-ácido clavulânico', NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (3,                        2,                    7,      'Benzilpenicilina',              NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        2,                    8,      'CEFOXITINA',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        2,                    9,      'Oxacilina',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        2,                    10,     'Penicilina',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        2,                    11,     'Piperacilina-tazobactam',       NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        2,                    12,     'Ticarcilina-ácido clavulânico', NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (4,                        2,                    13,     'Doripenem',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (4,                        2,                    14,     'Ertapenem',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (4,                        2,                    15,     'Imipenem',                      NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (4,                        2,                    16,     'Meropenem',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (5,                        1,                    17,     'Cefalexina',                    NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (5,                        1,                    18,     'Cefadroxil',                    NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (5,                        2,                    19,     'Cefalotina',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (5,                        2,                    20,     'Cefazolina',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (6,                        2,                    21,     'Cefuroxima',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (6,                        1,                    22,     'Cefaclor',                      NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (7,                        2,                    23,     'Ceftriaxona',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (7,                        2,                    24,     'CEFTAXIMA',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (7,                        2,                    25,     'Ceftazidima',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (8,                        2,                    26,     'Cefepima',                      NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (8,                        2,                    27,     'Cefpiroma',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (9,                        2,                    28,     'Kefazol',                       NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (10,                       2,                    29,     'Tigeciclina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (11,                       2,                    30,     'Vancomicina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (11,                       2,                    31,     'Teicoplanina',                  NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (11,                       2,                    32,     'Telavancina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (12,                       1,                    33,     'Sulfazotrim',                   NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (12,                       1,                    34,     'Trimetoprima-sulfametoxazol',   NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (13,                       2,                    35,     'Clindamicina',                  NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (14,                       1,                    36,     'Azitromicina',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (14,                       1,                    37,     'Eritromicina',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (15,                       1,                    38,     'Nitrofurantoína',               NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (16,                       1,                    39,     'Metronidazol',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (17,                       2,                    40,     'Colistina',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (17,                       2,                    41,     'Polimixina B',                  NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (18,                       1,                    42,     'Ciprofloxacina',                NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (18,                       1,                    43,     'Levofloxacina',                 NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (18,                       1,                    44,     'Moxifloxacina',                 NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (18,                       1,                    45,     'Norfloxacina',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (19,                       1,                    46,     'Tetraciclina',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (20,                       1,                    47,     'Fluconozol',                    NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (20,                       2,                    48,     'Voriconazol',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (20,                       1,                    49,     'Cetoconazol',                   NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (20,                       2,                    50,     'Miconazol',                     NOW(),     1,             NOW(),       1,               1        ), -- Tópica
                           (21,                       2,                    51,     'Capsofungina',                  NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (21,                       2,                    52,     'Micafungina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (22,                       2,                    53,     'Anfotericina B',                NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (22,                       3,                    54,     'Nistatina',                     NOW(),     1,             NOW(),       1,               1        ); -- Tópica
