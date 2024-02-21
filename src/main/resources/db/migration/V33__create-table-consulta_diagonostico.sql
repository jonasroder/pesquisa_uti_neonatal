CREATE TABLE consulta_diagonostico
(
    id_consulta_diagnostico BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta             BIGINT,
    id_diagnostico          BIGINT,
    is_active               TINYINT DEFAULT 1
);
