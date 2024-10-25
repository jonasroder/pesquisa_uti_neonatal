CREATE TABLE evento_via_administracao
(
    id_evento_via_administracao BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_evento                   BIGINT,
    id_via_administracao        BIGINT,
    criado_em                   DATETIME,
    criado_por_id               BIGINT,
    alterado_em                 DATETIME,
    alterado_por_id             BIGINT,
    is_active                   TINYINT DEFAULT 1
);
