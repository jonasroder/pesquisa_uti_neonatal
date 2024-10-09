CREATE TABLE mecanismo_resistencia_microorganismo
(
    id_mecanismo_resistencia_microorganismo BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo                                  BIGINT       NOT NULL,
    descricao                               VARCHAR(255) NULL,
    criado_em                               DATETIME     NULL,
    criado_por_id                           BIGINT       NULL,
    alterado_em                             DATETIME     NULL,
    alterado_por_id                         BIGINT       NULL,
    is_active                               TINYINT      NULL
);


INSERT INTO mecanismo_resistencia_microorganismo (codigo, descricao, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (0, 'Ausente', NULL, NULL, NULL, NULL, 1);
INSERT INTO mecanismo_resistencia_microorganismo (codigo, descricao, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (1, 'ORS', NULL, NULL, NULL, NULL, 1);
INSERT INTO mecanismo_resistencia_microorganismo (codigo, descricao, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (2, 'ESBL', NULL, NULL, NULL, NULL, 1);
INSERT INTO mecanismo_resistencia_microorganismo (codigo, descricao, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (3, 'CR', NULL, NULL, NULL, NULL, 1);