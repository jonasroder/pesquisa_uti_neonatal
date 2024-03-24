CREATE TABLE ingrediente
(
    id_ingrediente  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    descricao       TEXT,
    criado_em       DATETIME,
    criado_por_id   BIGINT,
    alterado_em     DATETIME,
    alterado_por_id BIGINT,
    is_active       TINYINT DEFAULT 1
);


INSERT INTO ingrediente (nome, descricao, is_active)
VALUES ('Melatonina', 'Hormônio envolvido na regulação do sono', 1);
INSERT INTO ingrediente (nome, descricao, is_active)
VALUES ('Mulungu', 'Erva tradicionalmente usada para promover a calma', 1);
INSERT INTO ingrediente (nome, descricao, is_active)
VALUES ('L-Teanina', 'Aminoácido que pode ajudar na promoção do relaxamento sem sonolência', 1);
INSERT INTO ingrediente (nome, descricao, is_active)
VALUES ('Extrato de Ashwagandha', 'Planta adaptogênica que pode ajudar a reduzir o estresse', 1);
INSERT INTO ingrediente (nome, descricao, is_active)
VALUES ('5-HTP', 'Precursor da serotonina, potencialmente melhora o humor e o sono', 1);
INSERT INTO ingrediente (nome, descricao, is_active)
VALUES ('Óleo de peixe Ômega-3', 'Rico em ácidos graxos EPA e DHA, beneficia a saúde do coração e do cérebro', 1);
INSERT INTO ingrediente (nome, descricao, is_active)
VALUES ('Curcumina', 'Composto ativo da cúrcuma, possui propriedades anti-inflamatórias', 1);