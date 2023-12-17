-- Criação da tabela de paciente
CREATE TABLE paciente
(
    id_paciente             INT AUTO_INCREMENT PRIMARY KEY,
    nome                    VARCHAR(255) NOT NULL,
    sobrenome               VARCHAR(255) NOT NULL,
    data_nascimento         DATE,
    telefone_1              VARCHAR(20),
    telefone_2              VARCHAR(20),
    email                   VARCHAR(255),
    cpf                     VARCHAR(11) UNIQUE,
    naturalidade            VARCHAR(250),
    nome_pai                VARCHAR(250),
    nome_mae                VARCHAR(250),
    nome_conjuge            VARCHAR(250),
    data_ultimo_atendimento DATE,
    num_visitas             INT,
    indicacao               VARCHAR(250),
    observacao              LONGTEXT,
    id_profissao            INT,
    id_plano_saude          INT,
    id_estado_civil         INT,
    id_sexo                 INT,
    id_etnia                INT,
    id_escolaridade         INT,
    id_religiao             INT,
    is_active               TINYINT(1) DEFAULT 1
);


INSERT INTO paciente (nome, sobrenome, data_nascimento, cpf, is_active)
VALUES ('Jonas', 'Röder', '1991-03-03', '06398404939', 1);
INSERT INTO paciente (nome, sobrenome, data_nascimento, cpf, is_active)
VALUES ('Denise', 'Röder', '1976-03-09', '03953126636', 1);
