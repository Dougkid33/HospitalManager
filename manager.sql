-- Active: 1686228775121@@127.0.0.1@3306@manager
CREATE TABLE `manager`. Pessoas (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(100),
    endereco VARCHAR(200),
    cpf VARCHAR(14),
    telefone VARCHAR(20),
    login VARCHAR(50),
    senha VARCHAR(50),
    tipoUsuario VARCHAR(50),
    dataCriacao DATE,
    dataModificacao DATE
);

CREATE TABLE `manager`. Medicos (
    id INT PRIMARY KEY,
    idPessoas INT NOT NULL,
    crm INT,
    especialidade VARCHAR(100),
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (idPessoas) REFERENCES Pessoas(id)
);

CREATE TABLE `manager`. Franquias (
    id INT auto_increment PRIMARY KEY NOT NULL,
    nome VARCHAR(100),
    cnpj VARCHAR(18),
    cidade VARCHAR(50),
    endereco VARCHAR(200),
    responsavel INT NOT NULL,
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (responsavel) REFERENCES Pessoas(id)
);

CREATE TABLE `manager`. Unidades (
    id INT auto_increment PRIMARY KEY NOT NULL,
    nome VARCHAR(100),
    cnpj VARCHAR(20),
    cidade VARCHAR(100),
    endereco VARCHAR(200),
    responsavel INT NOT NULL,
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (responsavel) REFERENCES Pessoas(id)
);

CREATE TABLE `manager`. FinanceiroADM (
    id INT auto_increment PRIMARY KEY NOT NULL,
    tipoMovimento VARCHAR(50),
    valor DOUBLE,
    -- DUVIDA SE UNIDADE VAI SER FOREIGN KEY OU NÃO
    unidade INT NOT NULL,
    descritivoMovimento VARCHAR(200),
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (unidade) REFERENCES Unidades(id)
);

CREATE TABLE `manager`. FinanceiroMedico (
    id INT auto_increment PRIMARY KEY NOT NULL,
    valorMedico DOUBLE,
    estado VARCHAR(50),
    franquia DOUBLE,
    descricao VARCHAR(200),
    dataCriacao DATE,
    dataModificacao DATE
);

CREATE TABLE `manager`. Consultas (
    id INT auto_increment PRIMARY KEY NOT NULL,
    data DATE,
    hora VARCHAR(10),
    estado VARCHAR(50),
    medico VARCHAR(100),
    paciente VARCHAR(100),
    valor DOUBLE,
    -- DUVIDA SE UNIDADE VAI SER FOREIGN KEY OU NÃO
    unidade INT NOT NULL,
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (unidade) REFERENCES Unidades(id)
);

CREATE TABLE `manager`. InfoConsultas (
    id INT auto_increment PRIMARY KEY NOT NULL,
    consulta INT NOT NULL,
    descricao TEXT,
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (consulta) REFERENCES Consultas(id)
);

CREATE TABLE `manager`. Procedimentos (
    id INT auto_increment PRIMARY KEY NOT NULL,
    nome VARCHAR(100),
    consulta INT NOT NULL,
    diaHorario DATETIME, --  "YYYY-MM-DD HH:MI:SS"
    estado VARCHAR(50),
    valor DOUBLE,
    laudo TEXT,
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (consulta) REFERENCES Consultas(id)
);

