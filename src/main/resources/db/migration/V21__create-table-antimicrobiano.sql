CREATE TABLE antimicrobiano
(
    id_antimicrobiano        BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_classe_antimicrobiano BIGINT,
    codigo                   BIGINT UNIQUE NOT NULL,
    descricao                VARCHAR(255)  NOT NULL,
    criado_em                DATETIME,
    criado_por_id            BIGINT,
    alterado_em              DATETIME,
    alterado_por_id          BIGINT,
    is_active                TINYINT DEFAULT 1
);

INSERT INTO antimicrobiano (id_classe_antimicrobiano, codigo, descricao,                       criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES                     (1,                        1,      'Amicacina',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (1,                        2,      'Gentamicina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (2,                        3,      'Rifampicina',                   NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (3,                        4,      'Ampicilina',                    NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (3,                        5,      'Ampicilina-sulbactam',          NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        6,      'Amoxicilina-ácido clavulânico', NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (3,                        7,      'Benzilpenicilina',              NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        8,      'CEFOXITINA',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        9,      'Oxacilina',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        10,     'Penicilina',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        11,     'Piperacilina-tazobactam',       NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (3,                        12,     'Ticarcilina-ácido clavulânico', NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (4,                        13,     'Doripenem',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (4,                        14,     'Ertapenem',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (4,                        15,     'Imipenem',                      NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (4,                        16,     'Meropenem',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (5,                        17,     'Cefalexina',                    NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (5,                        18,     'Cefadroxil',                    NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (5,                        19,     'Cefalotina',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (5,                        20,     'Cefazolina',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (6,                        21,     'Cefuroxima',                    NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (6,                        22,     'Cefaclor',                      NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (7,                        23,     'Ceftriaxona',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (7,                        24,     'CEFTAXIMA',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (7,                        25,     'Ceftazidima',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (8,                        26,     'Cefepima',                      NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (8,                        27,     'Cefpiroma',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (9,                        28,     'Kefazol',                       NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (10,                       29,     'Tigeciclina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (11,                       30,     'Vancomicina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (11,                       31,     'Teicoplanina',                  NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (11,                       32,     'Telavancina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (12,                       33,     'Sulfazotrim',                   NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (12,                       34,     'Trimetoprima-sulfametoxazol',   NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (13,                       35,     'Clindamicina',                  NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (14,                       36,     'Azitromicina',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (14,                       37,     'Eritromicina',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (15,                       38,     'Nitrofurantoína',               NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (16,                       39,     'Metronidazol',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (17,                       40,     'Colistina',                     NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (17,                       41,     'Polimixina B',                  NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (18,                       42,     'Ciprofloxacina',                NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (18,                       43,     'Levofloxacina',                 NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (18,                       44,     'Moxifloxacina',                 NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (18,                       45,     'Norfloxacina',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (19,                       46,     'Tetraciclina',                  NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (20,                       47,     'Fluconozol',                    NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (20,                       48,     'Voriconazol',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (20,                       49,     'Cetoconazol',                   NOW(),     1,             NOW(),       1,               1        ), -- Oral
                           (20,                       50,     'Miconazol',                     NOW(),     1,             NOW(),       1,               1        ), -- Tópica
                           (21,                       51,     'Capsofungina',                  NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (21,                       52,     'Micafungina',                   NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (22,                       53,     'Anfotericina B',                NOW(),     1,             NOW(),       1,               1        ), -- Intravenosa
                           (22,                       54,     'Nistatina',                     NOW(),     1,             NOW(),       1,               1        ); -- Tópica
