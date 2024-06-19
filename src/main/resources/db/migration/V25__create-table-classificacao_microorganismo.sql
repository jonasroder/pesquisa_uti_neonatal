CREATE TABLE classificacao_microorganismo
(
    id_classificacao_microorganismo BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo                          BIGINT       NOT NULL,
    descricao                       VARCHAR(255) NULL,
    criado_em                       DATETIME     NULL,
    criado_por_id                   BIGINT       NULL,
    alterado_em                     DATETIME     NULL,
    alterado_por_id                 BIGINT       NULL,
    is_active                       TINYINT      NULL
);

-- Inserindo dados na tabela classificacao_microorganismo
INSERT INTO classificacao_microorganismo (codigo, descricao,       criado_em,             criado_por_id, alterado_em,           alterado_por_id, is_active)
VALUES                                   (0,      'GRAM POSITIVO', '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                                         (1,      'GRAM NEGATIVO', '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        ),
                                         (2,      'FUNGO',         '2024-06-01 13:53:52', 1,             '2024-06-01 13:53:52', 1,               1        );
