CREATE TABLE prescricao_suplemento
(
    id_prescricao_suplemento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_consulta              BIGINT,
    id_suplemento            BIGINT,
    dosagem                  VARCHAR(255),
    instrucoes               TEXT,
    id_frequencia            BIGINT,
    inicio_tratamento        DATE,
    fim_tratamento           DATE,
    is_active                TINYINT DEFAULT 1
);
