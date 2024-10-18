CREATE TABLE classe_antimicrobiano
(
    id_classe_antimicrobiano BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_antimicrobiano   BIGINT,
    codigo                   BIGINT UNIQUE NOT NULL,
    descricao                VARCHAR(255)  NOT NULL,
    criado_em                DATETIME,
    criado_por_id            BIGINT,
    alterado_em              DATETIME,
    alterado_por_id          BIGINT,
    is_active                TINYINT DEFAULT 1
);


INSERT INTO classe_antimicrobiano (id_tipo_antimicrobiano, codigo, descricao,                            criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES                            (1,                      1,      'Aminoglicosídeos',                   NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      2,      'Ansamicinas',                        NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      3,      'Betalactâmicos',                     NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      4,      'Carbapenêmicos',                     NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      5,      'Cefalosporinas de Primeira Geração', NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      6,      'Cefalosporinas de Segunda Geração',  NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      7,      'Cefalosporinas de Terceira Geração', NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      8,      'Cefalosporinas de Quarta Geração',   NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      9,      'Cefalosporinas de Quinta Geração',   NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      10,     'Glicilciclinas',                     NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      11,     'Glicopeptídeos',                     NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      12,     'Inibidores de Folato',               NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      13,     'Lincosamidas',                       NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      14,     'Macrolídeos',                        NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      15,     'Nitrofurânicos',                     NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      16,     'Nitroimidazólicos',                  NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      17,     'Polimixinas',                        NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      18,     'Quinolonas',                         NOW(),     1,             NOW(),       1,               1        ),
                                  (1,                      19,     'Tetraciclinas',                      NOW(),     1,             NOW(),       1,               1        ),
                                  (2,                      20,     'Azóis',                              NOW(),     1,             NOW(),       1,               1        ),
                                  (2,                      21,     'Equinocandinas',                     NOW(),     1,             NOW(),       1,               1        ),
                                  (2,                      22,     'Polienos',                           NOW(),     1,             NOW(),       1,               1        );
