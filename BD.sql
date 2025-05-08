CREATE DATABASE SaaS;

USE SaaS;

CREATE TABLE Portifolio(
	id_img INT PRIMARY KEY AUTO_INCREMENT,
    id_prestador INT NOT NULL,
    caminho_img VARCHAR(255),
    
    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador)
);

CREATE TABLE Cliente(
	id_contratante INT AUTO_INCREMENT PRIMARY KEY,
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
    foto VARCHAR(255)
);

CREATE TABLE Servico(
	id_servico INT AUTO_INCREMENT UNIQUE,
    id_prestador INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    preco DOUBLE NOT NULL,
    descricao VARCHAR(100),
    dia DATE,
    horario DATETIME,
    STATUS ENUM('Aberto', 'Fechado') DEFAULT 'Aberto',
    
    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador)
);

CREATE TABLE Disponibilidade(
	id_disponilidade INT PRIMARY KEY AUTO_INCREMENT,
    id_prestador INT NOT NULL,
    dia_semana INT NOT NULL,
    comeco_descanso TIME NOT NULL,
    fim_descanso TIME NOT NULL,
    inicio_servico TIME NOT NULL,
    fim_servico TIME NOT NULL,
    tempo_servico TIME NOT NULL,
    
    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador),
    UNIQUE(id_prestador, dia_semana, comeco_descanso, fim_descanso, inicio_servico, fim_servico, tempo_servico)
);

