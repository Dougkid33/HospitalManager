CREATE TABLE Pessoas (
    id INT auto_increment PRIMARY KEY NOT NULL,
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

CREATE TABLE Medicos (
    id INT PRIMARY KEY,
    crm INT,
    especialidade VARCHAR(100),
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (id) REFERENCES Pessoas(id)
);

CREATE TABLE Franquias (
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

CREATE TABLE Unidades (
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

CREATE TABLE FinanceiroADM (
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

CREATE TABLE FinanceiroMedico (
    id INT auto_increment PRIMARY KEY NOT NULL,
    valorMedico DOUBLE,
    estado VARCHAR(50),
    franquia DOUBLE,
    descricao VARCHAR(200),
    dataCriacao DATE,
    dataModificacao DATE
);


CREATE TABLE Consultas (
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

CREATE TABLE InfoConsultas (
    id INT auto_increment PRIMARY KEY NOT NULL,
    consulta INT NOT NULL,
    descricao TEXT,
    dataCriacao DATE,
    dataModificacao DATE,
    FOREIGN KEY (consulta) REFERENCES Consultas(id)
);

CREATE TABLE Procedimentos (
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

