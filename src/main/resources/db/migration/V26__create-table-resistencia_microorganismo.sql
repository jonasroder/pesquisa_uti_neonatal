CREATE TABLE resistencia_microorganismo
(
    id_resistencia_microorganismo BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo                        BIGINT       NOT NULL,
    descricao                     VARCHAR(255) NULL,
    criado_em                     DATETIME     NULL,
    criado_por_id                 BIGINT       NULL,
    alterado_em                   DATETIME     NULL,
    alterado_por_id               BIGINT       NULL,
    is_active                     TINYINT      NULL
);


INSERT INTO pesquisaneonatos.resistencia_microorganismo (codigo, descricao, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (0, 'Sensível', NULL, NULL, NULL, NULL, 1);
INSERT INTO pesquisaneonatos.resistencia_microorganismo (codigo, descricao, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (1, 'Resistente', NULL, NULL, NULL, NULL, 1);
INSERT INTO pesquisaneonatos.resistencia_microorganismo (codigo, descricao, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (2, 'Sensível Aumentando a Exposição', NULL, NULL, NULL, NULL, 1);