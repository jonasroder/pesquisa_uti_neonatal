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


INSERT INTO paciente (nome, sobrenome, data_nascimento, telefone_1, telefone_2, email, cpf, naturalidade, nome_pai,
                      nome_mae, nome_conjuge, numero_plano_saude, num_visitas, indicacao, observacao, id_profissao,
                      id_plano_saude, id_estado_civil, id_sexo, id_etnia, id_escolaridade, id_religiao, is_active)
VALUES ('Jonas', 'Röder', '1991-03-03', '34996841061', '34991854234', 'jonas@gmail.com', '06398404939', 'Garuva',
        'Werner', 'Elfrida', 'Densie', '45698713548976', NULL, 'Internet', 'teste obs', 8, 1, 2, 1, 1, 4, 2, 1);
