-- Criação da tabela de pessoas
CREATE TABLE pessoas
(
    id_pessoa       INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_pessoa  INT,
    nome            VARCHAR(255) NOT NULL,
    sobrenome       VARCHAR(255) NOT NULL,
    id_sexo         INT,
    data_nascimento DATE         NOT NULL,
    telefone        VARCHAR(20),
    email           VARCHAR(255),
    id_estado_civil INT,
    id_profissao    INT,
    cpf             VARCHAR(11) UNIQUE,
    id_etnia        INT,
    id_escolaridade INT,
    id_religiao     INT,
    is_active       TINYINT(1) DEFAULT 1
);


INSERT INTO pessoas (id_tipo_pessoa, nome, sobrenome, data_nascimento, cpf, is_active)
VALUES (1, 'Jonas', 'Röder', '1991-03-03', '06398404939', 1);
INSERT INTO pessoas (id_tipo_pessoa, nome, sobrenome, data_nascimento, cpf, is_active)
VALUES (1, 'Denise', 'Röder', '1976-03-09', '03953126636', 1);
