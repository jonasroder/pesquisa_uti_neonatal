CREATE TABLE neonatos_ausencia_uti
(
    id_neonatos_ausencia_uti BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_neonato               BIGINT,
    data_saida_uti           DATE,
    data_retorno_uti         DATE,
    criado_em                DATETIME,
    criado_por_id            BIGINT,
    alterado_em              DATETIME,
    alterado_por_id          BIGINT,
    is_active                TINYINT DEFAULT 1
);
