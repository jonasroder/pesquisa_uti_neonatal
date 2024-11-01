CREATE TABLE neonato
(
    id_neonato           BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_mae             VARCHAR(255),
    prontuario           VARCHAR(255),
    data_nascimento      DATE,
    data_internacao      DATE,
    data_desfecho        DATE,
    obito                TINYINT,
    apgar_1              BIGINT,
    apgar_5              BIGINT,
    peso_gramas          BIGINT,
    id_sexo              BIGINT,
    id_peso_nascimento   BIGINT,
    id_motivo_internacao BIGINT,
    id_local_nascimento  BIGINT,
    id_idade_gestacional BIGINT,
    id_tipo_parto        BIGINT,
    id_rotura_membrana   BIGINT,
    id_causa_obito       BIGINT,
    risco_infeccioso     TINYINT,
    sepse_clinica        TINYINT,
    criado_em            DATETIME,
    criado_por_id        BIGINT,
    alterado_em          DATETIME,
    alterado_por_id      BIGINT,
    is_active            TINYINT DEFAULT 1
);

INSERT INTO neonato (nome_mae, prontuario, data_nascimento, data_internacao, data_desfecho, obito, apgar_1, apgar_5, peso_gramas, id_sexo, id_peso_nascimento, id_motivo_internacao, id_local_nascimento, id_idade_gestacional, id_tipo_parto, id_rotura_membrana, id_causa_obito, risco_infeccioso, sepse_clinica, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES ('Thais Gonçalves ', '1345339', '2015-05-01', '2015-05-02', '2015-05-04', 1, 0, 0, 675, 1, 5, 19, 1, 5, 2, 3, NULL, 0, 0, '2024-05-06 11:48:25', 4, NULL, NULL, 1);