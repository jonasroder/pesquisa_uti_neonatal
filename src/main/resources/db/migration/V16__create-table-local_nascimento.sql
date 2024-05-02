CREATE TABLE local_nascimento
(
    id_local_nascimento BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo              BIGINT UNIQUE NOT NULL,
    descricao           VARCHAR(255)  NOT NULL,
    criado_em           DATETIME,
    criado_por_id       BIGINT,
    alterado_em         DATETIME,
    alterado_por_id     BIGINT,
    is_active           TINYINT DEFAULT 1
);


INSERT INTO local_nascimento (codigo, descricao, criado_em, alterado_em, is_active)
VALUES (0, 'HC-UFU', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       (1, 'Outro serviço', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
