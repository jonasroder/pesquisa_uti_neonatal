CREATE TABLE consulta_informacao_saude
(
    id_consulta_informacao_saude BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta                  BIGINT,
    id_tipo_informacao_saude     BIGINT,
    valor                        VARCHAR(255) NOT NULL,
    criado_em                    DATETIME,
    criado_por_id                BIGINT,
    alterado_em                  DATETIME,
    alterado_por_id              BIGINT,
    is_active                    TINYINT DEFAULT 1
);
