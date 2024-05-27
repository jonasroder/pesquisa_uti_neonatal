CREATE TABLE tipo_evento
(
    id_tipo_evento  BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo          BIGINT UNIQUE NOT NULL,
    descricao       VARCHAR(255)  NOT NULL,
    cor_associada   VARCHAR(7),
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO tipo_evento (codigo, descricao, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active,
                         cor_associada)
VALUES (1, 'Medicação com Antibiótico', NOW(), 1, NOW(), 1, 1, '#FF5733'), -- Vermelho
       (2, 'Dreno', NOW(), 1, NOW(), 1, 1, '#FFBD33'),                     -- Laranja
       (3, 'Intubação', NOW(), 1, NOW(), 1, 1, '#FF33A1'),                 -- Rosa
       (4, 'Cateter/Sonda Vesical', NOW(), 1, NOW(), 1, 1, '#33FF57'),     -- Verde
       (5, 'Nutrição Parenteral', NOW(), 1, NOW(), 1, 1, '#33FFBD'),       -- Verde água
       (6, 'CVC PICC', NOW(), 1, NOW(), 1, 1, '#3357FF'),                  -- Azul
       (7, 'CVC Umbilical', NOW(), 1, NOW(), 1, 1, '#8A33FF'),             -- Roxo
       (8, 'CVC Flebotomia', NOW(), 1, NOW(), 1, 1, '#FF33E3'),            -- Magenta
       (9, 'Cirurgia', NOW(), 1, NOW(), 1, 1, '#FF8A33'); -- Laranja escuro
