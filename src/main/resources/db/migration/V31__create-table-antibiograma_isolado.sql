CREATE TABLE antibiograma_isolado
(
    id_antibiograma_isolado       BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_isolado_coleta             BIGINT   NOT NULL,
    id_antimicrobiano             BIGINT   NULL,
    id_resistencia_microorganismo BIGINT   NULL,
    criado_em                     DATETIME NULL,
    criado_por_id                 BIGINT   NULL,
    alterado_em                   DATETIME NULL,
    alterado_por_id               BIGINT   NULL,
    is_active                     TINYINT  NULL
);

