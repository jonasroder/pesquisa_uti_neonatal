CREATE TABLE usuario
(
    id_usuario      BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_role         BIGINT       NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    senha           VARCHAR(255) NOT NULL,
    nome_completo   VARCHAR(255) NOT NULL,
    telefone        VARCHAR(20),
    cpf             VARCHAR(11)  NOT NULL UNIQUE,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

-- Inserindo dados na tabela usuario
INSERT INTO usuario (id_role, email, senha, nome_completo, telefone, cpf, criado_em, criado_por_id, alterado_em, alterado_por_id, is_active)
VALUES (1, 'jonasroder@gmail.com', '$2a$10$zH5icg1I/3C8Ch7RmSvjI.vM9R6L/ScwatoV.L7ai9xZ1vcke5cES', 'Jonas Roder', '34996841061', '06398404939', NOW(), 1, NULL, NULL, 1);
