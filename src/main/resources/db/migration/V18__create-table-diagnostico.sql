CREATE TABLE diagnostico
(
    id_diagnostico  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    descricao       TEXT,
    codigo_cid      VARCHAR(50),
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO diagnostico (nome, descricao, codigo_cid, is_active)
VALUES ('Hipertensão Essencial (Primária)', 'Pressão arterial cronicamente elevada sem causa secundária identificável',
        'I10', 1),
       ('Diabetes Mellitus Tipo 2',
        'Distúrbio metabólico caracterizado por alta glicose no sangue em contexto de resistência à insulina', 'E11',
        1),
       ('Gripe', 'Infecção viral aguda do sistema respiratório', 'J11', 1),
       ('Asma', 'Doença inflamatória crônica das vias aéreas', 'J45', 1),
       ('Depressão', 'Transtorno de humor caracterizado por tristeza persistente e perda de interesse', 'F32', 1),
       ('Ansiedade Generalizada', 'Transtorno caracterizado por ansiedade e preocupação excessivas', 'F41.1', 1),
       ('Obesidade', 'Acúmulo excessivo de gordura corporal', 'E66', 1),
       ('Dor Lombar', 'Dor na região lombar da coluna', 'M54.5', 1),
       ('Infecção do Trato Urinário', 'Infecção que afeta o trato urinário', 'N39.0', 1),
       ('Conjuntivite', 'Inflamação ou infecção da conjuntiva do olho', 'H10', 1);
