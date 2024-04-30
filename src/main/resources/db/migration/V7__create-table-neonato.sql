CREATE TABLE neonato
(
    id_neonato      BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_mae        VARCHAR(255),
    prontuario      VARCHAR(255),
    data_nascimento DATE,
    data_internacao DATE,
    data_desfecho   DATE,
    obito           TINYINT,
    peso            BIGINT,
    id_sexo         BIGINT,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);
