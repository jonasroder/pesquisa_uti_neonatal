-- Criação da tabela que liga usuários e permissões
CREATE TABLE usuario_permissoes
(
    id_usuario_permissoes INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario            INT,
    id_permissao          INT,
    is_active             TINYINT(1) DEFAULT 1
);
