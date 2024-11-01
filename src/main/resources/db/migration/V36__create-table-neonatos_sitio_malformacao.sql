CREATE TABLE neonato_sitio_malformacao
(
    id_neonato_sitio_malformacao BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_neonato                   BIGINT,
    id_sitio_malformacao         BIGINT,
    criado_em                    DATETIME,
    criado_por_id                BIGINT,
    alterado_em                  DATETIME,
    alterado_por_id              BIGINT,
    is_active                    TINYINT DEFAULT 1
);
