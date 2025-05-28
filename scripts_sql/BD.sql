DROP DATABASE IF EXISTS SaaS;

CREATE DATABASE IF NOT EXISTS SaaS;

USE SaaS;

SELECT * FROM Servico;

CREATE TABLE Cliente(
	id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(256) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    telefone VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Prestador(
	id_prestador INT AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(256) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    usuário VARCHAR(100) NOT NULL,
    cpf VARCHAR(100) NOT NULL UNIQUE,
    caminho_img VARCHAR(255),
    telefone VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Especialidade(
	id_especialidade INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(100)
);

CREATE TABLE PrestadorEspecialidade(
	id_prestador INT NOT NULL,
    id_especialidade INT NOT NULL,
    
    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador),
    FOREIGN KEY (id_especialidade) REFERENCES Especialidade(id_especialidade),
    
    PRIMARY KEY (id_prestador, id_especialidade)
);

CREATE TABLE Servico(
	id_servico INT AUTO_INCREMENT UNIQUE,
    id_prestador INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(100),
    status_servico INT NOT NULL,
    local VARCHAR(100),

    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador)
);

CREATE TABLE Disponibilidade(
	id_disponibilidade INT PRIMARY KEY AUTO_INCREMENT,
    id_servico INT NOT NULL,
    dia_semana INT NOT NULL,
    comeco_descanso TIME NOT NULL,
    fim_descanso TIME NOT NULL,
    inicio_servico TIME NOT NULL,
    fim_servico TIME NOT NULL,
    
    UNIQUE(dia_semana, comeco_descanso, fim_descanso, inicio_servico, fim_servico),
	FOREIGN KEY (id_servico) REFERENCES Servico(id_servico)
);

CREATE TABLE Portifolio(
	id_img INT PRIMARY KEY AUTO_INCREMENT,
    id_servico INT NOT NULL,
    caminho_img VARCHAR(255),
    
    FOREIGN KEY (id_servico) REFERENCES Servico(id_servico)
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

