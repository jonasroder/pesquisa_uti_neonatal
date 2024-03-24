CREATE TABLE uf
(
    id_uf           BIGINT AUTO_INCREMENT PRIMARY KEY,
    sigla           VARCHAR(2)   NOT NULL,
    descricao       VARCHAR(255) NOT NULL,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);

-- Inserts para a tabela de UF
INSERT INTO uf (sigla, descricao)
VALUES ('AC', 'Acre');
INSERT INTO uf (sigla, descricao)
VALUES ('AL', 'Alagoas');
INSERT INTO uf (sigla, descricao)
VALUES ('AP', 'Amapá');
INSERT INTO uf (sigla, descricao)
VALUES ('AM', 'Amazonas');
INSERT INTO uf (sigla, descricao)
VALUES ('BA', 'Bahia');
INSERT INTO uf (sigla, descricao)
VALUES ('CE', 'Ceará');
INSERT INTO uf (sigla, descricao)
VALUES ('DF', 'Distrito Federal');
INSERT INTO uf (sigla, descricao)
VALUES ('ES', 'Espírito Santo');
INSERT INTO uf (sigla, descricao)
VALUES ('GO', 'Goiás');
INSERT INTO uf (sigla, descricao)
VALUES ('MA', 'Maranhão');
INSERT INTO uf (sigla, descricao)
VALUES ('MT', 'Mato Grosso');
INSERT INTO uf (sigla, descricao)
VALUES ('MS', 'Mato Grosso do Sul');
INSERT INTO uf (sigla, descricao)
VALUES ('MG', 'Minas Gerais');
INSERT INTO uf (sigla, descricao)
VALUES ('PA', 'Pará');
INSERT INTO uf (sigla, descricao)
VALUES ('PB', 'Paraíba');
INSERT INTO uf (sigla, descricao)
VALUES ('PR', 'Paraná');
INSERT INTO uf (sigla, descricao)
VALUES ('PE', 'Pernambuco');
INSERT INTO uf (sigla, descricao)
VALUES ('PI', 'Piauí');
INSERT INTO uf (sigla, descricao)
VALUES ('RJ', 'Rio de Janeiro');
INSERT INTO uf (sigla, descricao)
VALUES ('RN', 'Rio Grande do Norte');
INSERT INTO uf (sigla, descricao)
VALUES ('RS', 'Rio Grande do Sul');
INSERT INTO uf (sigla, descricao)
VALUES ('RO', 'Rondônia');
INSERT INTO uf (sigla, descricao)
VALUES ('RR', 'Roraima');
INSERT INTO uf (sigla, descricao)
VALUES ('SC', 'Santa Catarina');
INSERT INTO uf (sigla, descricao)
VALUES ('SP', 'São Paulo');
INSERT INTO uf (sigla, descricao)
VALUES ('SE', 'Sergipe');
INSERT INTO uf (sigla, descricao)
VALUES ('TO', 'Tocantins');
