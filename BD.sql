CREATE DATABASE SaaS;

USE SaaS;

CREATE TABLE Portifolio(
	id_img INT PRIMARY KEY AUTO_INCREMENT,
    id_servico INT NOT NULL,
    caminho_img VARCHAR(255),
    
    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador)
);

CREATE TABLE Cliente(
	id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL, -- criptografar
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Prestador(
	id_prestador INT AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL, -- criptografar
    nome VARCHAR(100) NOT NULL,
    nome_fantasia VARCHAR(100) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    cpf VARCHAR(100) NOT NULL UNIQUE,
    caminho_img VARCHAR(255)
);

CREATE TABLE Servico(
	id_servico INT AUTO_INCREMENT UNIQUE,
    id_prestador INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    preco DOUBLE NOT NULL,
    descricao VARCHAR(100),
    id_diponibilidade INT NOT NULL,
    status_servico ENUM('Disponivel', 'Indisponivel') DEFAULT 'Disponivel',
    local VARCHAR(100),

    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador),
    FOREIGN KEY (id_disponibilidade) REFERENCES Disponibilidade(id_disponibilidade)
);

CREATE TABLE Disponibilidade(
	id_disponilidade INT PRIMARY KEY AUTO_INCREMENT,
    id_servico INT NOT NULL,
    dia_semana INT NOT NULL,
    comeco_descanso TIME NOT NULL,
    fim_descanso TIME NOT NULL,
    inicio_servico TIME NOT NULL,
    fim_servico TIME NOT NULL,
    tempo_servico TIME NOT NULL,
    
    FOREIGN KEY (id_servico) REFERENCES Servico(id_servico),
    UNIQUE(id_servico, dia_semana, comeco_descanso, fim_descanso, inicio_servico, fim_servico, tempo_servico)
);

CREATE TABLE Agendamento(
	id_agendamento INT PRIMARY KEY AUTO_INCREMENT,
    id_servico INT NOT NULL,
    id_cliente INT NOT NULL,
    status_agendamento ENUM('Ativo', 'Finalizado', 'Cancelado') DEFAULT 'Ativo',
    horario DATETIME,
    
    FOREIGN KEY (id_servico) REFERENCES Servico(id_servico),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

