CREATE TABLE tipo_evento_agenda
(
    id_tipo_evento_agenda BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                  VARCHAR(50) NOT NULL,
    descricao             TEXT,
    cor_associada         VARCHAR(7),
    criado_em             DATETIME,
    criado_por_id         BIGINT,
    alterado_em           DATETIME,
    alterado_por_id       BIGINT,
    is_active             TINYINT DEFAULT 1
);

INSERT INTO tipo_evento_agenda (nome, descricao, cor_associada)
VALUES ('Consulta', 'Consulta médica para avaliação ou acompanhamento.', '#3498db'),
       ('Retorno', 'Consulta de acompanhamento após tratamento ou exame.', '#5dade2'),
       ('Implante Hormonal', 'Procedimento de inserção de dispositivo hormonal.', '#9b59b6'),
       ('Injeção', 'Aplicação de medicamento ou vacina.', '#8e44ad'),
       ('Exame de Rotina', 'Exames periódicos para monitoramento da saúde.', '#f1c40f'),
       ('Urgência', 'Atendimento emergencial para casos urgentes.', '#e74c3c'),
       ('Check-up', 'Avaliação geral da saúde do paciente.', '#f39c12'),
       ('Revisão de Exames', 'Consulta para análise de resultados de exames.', '#5da6f2'),
       ('Terapia', 'Sessões de terapia ou aconselhamento.', '#16a085'),
       ('Cirurgia Menor', 'Procedimentos cirúrgicos de pequeno porte.', '#27ae60');
