CREATE TABLE role
(
    id_role         BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(50) UNIQUE NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

-- Inserindo dados na tabela role
INSERT INTO role (nome,            is_active)
VALUES           ('ADMIN',         1        ),
                 ('MEDICO',        1        ),
                 ('RECEPCIONISTA', 1        ),
                 ('PACIENTE',      1        );
