CREATE TABLE suplemento
(
    id_suplemento  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome           VARCHAR(255) NOT NULL,
    descricao      TEXT,
    dosagem        VARCHAR(255),
    instrucoes_uso TEXT,
    is_active      TINYINT DEFAULT 1
);
