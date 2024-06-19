CREATE TABLE sexo
(
    id_sexo         BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo          BIGINT UNIQUE NOT NULL,
    descricao       VARCHAR(255)  NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

INSERT INTO sexo (codigo, descricao,       criado_em,         alterado_em,       is_active)
VALUES           (0,      'Masculino',     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                 (1,      'Feminino',      CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        ),
                 (2,      'Não Informado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1        );
