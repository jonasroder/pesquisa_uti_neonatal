CREATE TABLE tipo_consulta
(
    id_tipo_consulta BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao        VARCHAR(255) NOT NULL,
    criado_em        DATETIME,
    criado_por_id    BIGINT,
    alterado_em      DATETIME,
    alterado_por_id  BIGINT,
    is_active        TINYINT DEFAULT 1
);

-- Inserções iniciais na tabela tipo_consulta
INSERT INTO tipo_consulta (descricao, is_active)
VALUES ('Consulta', 1);

INSERT INTO tipo_consulta (descricao, is_active)
VALUES ('Retorno', 1);
