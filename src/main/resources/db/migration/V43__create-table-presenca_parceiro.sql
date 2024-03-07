CREATE TABLE presenca_parceiro
(
    id_presenca_parceiro TINYINT AUTO_INCREMENT PRIMARY KEY,
    descricao            VARCHAR(100) NOT NULL,
    is_active            TINYINT DEFAULT 1
);

INSERT INTO presenca_parceiro (descricao)
VALUES ('Não'),
       ('Parceiro ou colega, mas em outro quarto'),
       ('Parceiro no mesmo quarto, mas não na mesma cama'),
       ('Parceiro na mesma cama');
