CREATE TABLE status_agenda
(
    id_status_agenda BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome             VARCHAR(50) NOT NULL,
    descricao        TEXT,
    cor_associada    VARCHAR(7),
    criado_em        DATETIME,
    criado_por_id    BIGINT,
    alterado_em      DATETIME,
    alterado_por_id  BIGINT,
    is_active        TINYINT DEFAULT 1
);

INSERT INTO status_agenda (nome, descricao, cor_associada)
VALUES ('Confirmado', 'A consulta foi confirmada pelo paciente.', '#2ecc71'),
       ('Pendente', 'A consulta está agendada mas ainda não confirmada.', '#f39c12'),
       ('Cancelado', 'A consulta foi cancelada.', '#e74c3c'),
       ('Realizado', 'A consulta foi realizada.', '#3498db'),
       ('Não Compareceu', 'O paciente não compareceu à consulta.', '#95a5a6');
