CREATE TABLE usuario
(
    id_usuario      BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario         VARCHAR(255) NOT NULL,
    senha           VARCHAR(255) NOT NULL,
    nome_completo   VARCHAR(255) NOT NULL,
    titulo          VARCHAR(10),
    especialidade   VARCHAR(255),
    telefone_1      VARCHAR(20),
    telefone_2      VARCHAR(20),
    cpf             VARCHAR(11)  NOT NULL UNIQUE,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

-- Inserindo dados na tabela usuario
INSERT INTO usuario (usuario,           senha,                                                          nome_completo,      titulo, especialidade, telefone_1,    telefone_2,    cpf,           criado_em,             criado_por_id, alterado_em,           alterado_por_id, is_active)
VALUES              ('jonasroder',      '$2a$10$zH5icg1I/3C8Ch7RmSvjI.vM9R6L/ScwatoV.L7ai9xZ1vcke5cES', 'Jonas Roder',      NULL,   NULL,          '34991854234', '34996841061', '06398404939', NULL,                  NULL,          '2024-05-04 16:08:53', 1,               1        ),
                    ('deniseroder',     '$2a$10$O3L.G5znbNT6946R/bA4ceesZ9iGZYbqADnKptgPTFi.Aeo12u4Fq', 'Denise Roder',     NULL,   NULL,          '34991854234', '34996841061', '03953126636', NULL,                  NULL,          '2024-05-04 16:12:07', 1,               1        ),
                    ('isadoracaixeta',  '$2a$10$EDR.5aP3aNZ9WjX6/IWFB.X5QcQfx93e7nkIVvM0piRJzF1ERPJEK', 'Isadora Caixeta',  NULL,   NULL,          '34991854234', '34996841061', '11111111112', '2024-05-04 15:42:42', 1,             NULL,                  NULL,            1        ),
                    ('ralcianemenezes', '$2a$10$MdpyCRE5MulVspewOf8K5eFrxuHIzp907HD6orz279dfs4B39kufK', 'Ralciane Menezes', NULL,   NULL,          '34991854234', '34996841061', '11111111113', '2024-05-04 15:45:37', 1,             NULL,                  NULL,            1        ),
                    ('mallulopes',      '$2a$10$RMgtqDh2q95trawXETOzYeK0As6zahkX9SHqiNx8GcdfXCNhgB7Oe', 'Mallu Lopes',      NULL,   NULL,          '34991854234', '34996841061', '11111111114', '2024-05-04 15:46:45', 1,             NULL,                  NULL,            1        );
