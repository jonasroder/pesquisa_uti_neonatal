CREATE TABLE endereco
(
    id_endereco     BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente     BIGINT NOT NULL,
    logradouro      VARCHAR(255),
    numero          VARCHAR(10),
    complemento     VARCHAR(255),
    bairro          VARCHAR(100),
    cidade          VARCHAR(100),
    id_uf           BIGINT,
    cep             VARCHAR(8),
    referencia      VARCHAR(255),
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
)

