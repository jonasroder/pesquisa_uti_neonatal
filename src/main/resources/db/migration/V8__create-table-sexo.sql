CREATE TABLE sexo
(
    id_sexo         BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);



INSERT INTO sexo (descricao, is_active)
VALUES ('Masculino', 1);
INSERT INTO sexo (descricao, is_active)
VALUES ('Feminino', 1);
INSERT INTO sexo (descricao, is_active)
VALUES ('Não especificado', 1);
