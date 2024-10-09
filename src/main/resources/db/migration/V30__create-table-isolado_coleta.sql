CREATE TABLE isolado_coleta
(
    id_isolado_coleta                       BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_evento                               BIGINT   NOT NULL,
    id_microorganismo                       BIGINT   NOT NULL,
    id_perfil_resistencia_microorganismo    BIGINT   NOT NULL,
    id_mecanismo_resistencia_microorganismo BIGINT   NOT NULL,
    criado_em                               DATETIME NULL,
    criado_por_id                           BIGINT   NULL,
    alterado_em                             DATETIME NULL,
    alterado_por_id                         BIGINT   NULL,
    is_active                               TINYINT  NULL
);

