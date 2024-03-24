CREATE TABLE suplemento
(
    id_suplemento   BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    descricao       TEXT,
    dosagem         VARCHAR(255),
    instrucoes_uso  TEXT,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO suplemento (nome, descricao, dosagem, instrucoes_uso, is_active)
VALUES ('Whey Protein', 'Suplemento proteico para recuperação muscular.', '30g', 'Consumir após o treino.', 1),
       ('BCAA', 'Aminoácidos de cadeia ramificada para auxílio na fadiga muscular.', '10g',
        'Consumir antes e depois do treino.', 1),
       ('Creatina', 'Ajuda no desempenho físico e aumento da força.', '5g', 'Consumir uma vez ao dia.', 1),
       ('Vitamina D', 'Suplemento para saúde óssea e sistema imunológico.', '5000IU',
        'Consumir uma vez ao dia, pela manhã.', 1),
       ('Ômega 3', 'Ácidos graxos essenciais para saúde cardiovascular.', '1000mg',
        'Consumir duas vezes ao dia com as refeições.', 1);
