CREATE TABLE solicitacao_download
(
    id_solicitacao_download BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao               VARCHAR(255) NOT NULL,
    filtros                 TEXT,
    status                  ENUM ('PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDO', 'FALHA') DEFAULT 'PENDENTE',
    criado_em               DATETIME,
    criado_por_id           BIGINT,
    alterado_em             DATETIME,
    alterado_por_id         BIGINT,
    is_active               TINYINT                                                 DEFAULT 1
);
