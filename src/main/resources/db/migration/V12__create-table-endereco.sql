-- Criação da tabela de endereço
CREATE TABLE endereco
(
    id_endereco INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT          NOT NULL,
    logradouro  VARCHAR(255) NOT NULL,
    numero      VARCHAR(10),
    complemento VARCHAR(255),
    bairro      VARCHAR(100),
    cidade      VARCHAR(100) NOT NULL,
    id_uf       INT          NOT NULL,
    cep         VARCHAR(8)   NOT NULL,
    referencia  VARCHAR(255)
)

