CREATE TABLE religiao
(
    id_religiao     BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao       VARCHAR(255) NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO religiao (descricao)
VALUES ('Católico');
INSERT INTO religiao (descricao)
VALUES ('Evangélico');
INSERT INTO religiao (descricao)
VALUES ('Espírita');
INSERT INTO religiao (descricao)
VALUES ('Budista');
INSERT INTO religiao (descricao)
VALUES ('Judeu');
INSERT INTO religiao (descricao)
VALUES ('Muçulmano');
INSERT INTO religiao (descricao)
VALUES ('Candomblecista');
INSERT INTO religiao (descricao)
VALUES ('Umbandista');
INSERT INTO religiao (descricao)
VALUES ('Hindu');