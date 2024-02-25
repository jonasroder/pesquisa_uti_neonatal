-- Criação da tabela de paciente
CREATE TABLE paciente
(
    id_paciente        BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome               VARCHAR(255) NOT NULL,
    sobrenome          VARCHAR(255) NOT NULL,
    data_nascimento    DATE,
    telefone_1         VARCHAR(20),
    telefone_2         VARCHAR(20),
    email              VARCHAR(255),
    cpf                VARCHAR(11) UNIQUE,
    naturalidade       VARCHAR(250),
    nome_pai           VARCHAR(250),
    nome_mae           VARCHAR(250),
    nome_conjuge       VARCHAR(250),
    numero_plano_saude VARCHAR(250),
    num_visitas        BIGINT,
    indicacao          VARCHAR(250),
    observacao         LONGTEXT,
    id_profissao       BIGINT,
    id_plano_saude     BIGINT,
    id_estado_civil    BIGINT,
    id_sexo            BIGINT,
    id_etnia           BIGINT,
    id_escolaridade    BIGINT,
    id_religiao        BIGINT,
    is_active          TINYINT DEFAULT 1
);

