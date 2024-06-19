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