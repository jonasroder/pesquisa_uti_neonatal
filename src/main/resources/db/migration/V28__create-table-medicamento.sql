CREATE TABLE medicamento
(
    id_medicamento            BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                      VARCHAR(255) NOT NULL,
    dosagem                   VARCHAR(255),
    id_fabricante_medicamento BIGINT,
    instrucoes                TEXT,
    criado_em                 DATETIME,
    criado_por_id             BIGINT,
    alterado_em               DATETIME,
    alterado_por_id           BIGINT,
    is_active                 TINYINT DEFAULT 1
);


INSERT INTO medicamento (nome, dosagem, id_fabricante_medicamento, instrucoes, is_active)
VALUES ('Viagra', '50mg', 1, 'Tomar uma hora antes da atividade sexual', 1),
       ('Tylenol', '750mg', 2, 'Tomar a cada 6 horas', 1),
       ('Losartana Potássica', '50mg', 3, 'Tomar uma vez ao dia', 1),
       ('Addera D3', '1000 UI', 4, 'Tomar uma vez ao dia', 1),
       ('Torsilax', '10mg', 5, 'Tomar uma dose a cada 24 horas', 1),
       ('Omeprazol', '20mg', 6, 'Tomar uma vez ao dia antes do café da manhã', 1),
       ('Dorflex', '300mg+35mg+50mg', 7, 'Tomar de 1 a 2 comprimidos, 3 vezes ao dia', 1),
       ('Neosaldina', '300mg+30mg+30mg', 8, 'Tomar a cada 6 horas', 1),
       ('Xarelto', '20mg', 1, 'Tomar uma vez ao dia', 1),
       ('Concerta', '36mg', 2, 'Tomar uma vez pela manhã', 1);