CREATE TABLE ingrediente
(
    id_ingrediente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome           VARCHAR(255) NOT NULL,
    descricao      TEXT,
    is_active      TINYINT DEFAULT 1
);
