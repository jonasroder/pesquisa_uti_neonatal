CREATE TABLE frequencia
(
    id_frequencia BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao     VARCHAR(255),
    is_active     TINYINT DEFAULT 1
);


INSERT INTO frequencia (descricao, is_active)
VALUES
    ('Sob demanda', 1),
    ('A cada 2 horas', 1),
    ('A cada 3 horas', 1),
    ('A cada 4 horas', 1),
    ('A cada 6 horas', 1),
    ('A cada 8 horas', 1),
    ('A cada 12 horas', 1),
    ('Uma vez ao dia', 1),
    ('Duas vezes ao dia', 1),
    ('Três vezes ao dia', 1),
    ('Quatro vezes ao dia', 1),
    ('Cinco vezes ao dia', 1),
    ('Seis vezes ao dia', 1),
    ('Uma vez por semana', 1),
    ('Duas vezes por semana', 1),
    ('Três vezes por semana', 1),
    ('Quatro vezes por semana', 1),
    ('Uma vez a cada duas semanas', 1),
    ('Uma vez por mês', 1),
    ('A cada dois meses', 1),
    ('A cada três meses', 1),
    ('A cada seis meses', 1);

