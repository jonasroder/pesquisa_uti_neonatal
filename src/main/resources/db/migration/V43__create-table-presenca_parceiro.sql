CREATE TABLE presenca_parceiro
(
    id_presenca_parceiro TINYINT AUTO_INCREMENT PRIMARY KEY,
    descricao            VARCHAR(100) NOT NULL,
    criado_em            DATETIME,
    criado_por_id        BIGINT,
    alterado_em          DATETIME,
    alterado_por_id      BIGINT,
    is_active            TINYINT DEFAULT 1
);

INSERT INTO presenca_parceiro (descricao)
VALUES ('Não'),
       ('Parceiro ou colega, mas em outro quarto'),
       ('Parceiro no mesmo quarto, mas não na mesma cama'),
       ('Parceiro na mesma cama');
