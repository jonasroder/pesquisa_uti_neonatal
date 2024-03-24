CREATE TABLE consulta_diagnostico
(
    id_consulta_diagnostico BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta             BIGINT,
    id_diagnostico          BIGINT,
    criado_em               DATETIME,
    criado_por_id           BIGINT,
    alterado_em             DATETIME,
    alterado_por_id         BIGINT,
    is_active               TINYINT DEFAULT 1
);
