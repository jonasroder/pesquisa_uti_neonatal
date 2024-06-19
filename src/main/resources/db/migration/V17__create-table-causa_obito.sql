CREATE TABLE causa_obito
(
    id_causa_obito  BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo          BIGINT UNIQUE NOT NULL,
    descricao       VARCHAR(255)  NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO causa_obito (codigo, descricao,                    criado_em,         alterado_em,       is_active)
VALUES                  (0,      'Insuficiência cardíaca',     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                        (1,      'Complicações respiratórias', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                        (2,      'Infecção generalizada',      CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        );
