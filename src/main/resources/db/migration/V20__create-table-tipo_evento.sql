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


INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (1, 'Medicação', '#FF5733', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (2, 'Dreno', '#FFC300', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (3, 'Entubação', '#C70039', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (4, 'Cateter/Sonda Vesical', '#900C3F', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (5, 'Nutrição Parenteral', '#581845', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (6, 'PICC', '#1F618D', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (7, 'CVU', '#28B463', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (8, 'Flebotomia', '#DAF7A6', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (9, 'Cirurgia', '#FF8A33', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);
INSERT INTO tipo_evento (codigo, descricao, cor_associada, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (10, 'Coleta', '#8E44AD', '2024-06-01 13:53:49', 1, '2024-06-01 13:53:49', 1, 1);