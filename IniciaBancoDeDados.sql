CREATE TABLE usuario(
  matricula VARCHAR(255),
  senha VARCHAR(255),
  nivel_acesso VARCHAR(255)
);
INSERT INTO usuario(matricula, senha, nivel_acesso) VALUES ('tecnico', 'tecnico', 1);
INSERT INTO usuario(matricula, senha, nivel_acesso) VALUES ('secretario', 'secretario', 2);
INSERT INTO usuario(matricula, senha, nivel_acesso) VALUES ('diretor', 'diretor', 3);

CREATE TABLE associacao(
numero_oficio VARCHAR(255),
data_oficio VARCHAR(255),
nome VARCHAR(255),
sigla VARCHAR(255),
endereco VARCHAR(255),
telefone VARCHAR(255),
comprovante_pagamento VARCHAR(255),
matricula VARCHAR(255),
senha VARCHAR(255));
INSERT INTO associacao(numero_oficio, data_oficio, nome, sigla, endereco, telefone, comprovante_pagamento, matricula, senha) VALUES ('teste','teste','teste','teste','teste','teste','teste','teste','teste')

CREATE TABLE atleta(
matricula VARCHAR(255) primary key,
nome VARCHAR(255),
numero VARCHAR(255),
data_entrada VARCHAR(255),
data_oficio VARCHAR(255),
data_nascimento VARCHAR(255),
comprovante_pagamento VARCHAR(255),
matricula_associacao VARCHAR(255),
foreign key (matricula_associacao) references associacao(matricula));
INSERT INTO atleta(matricula, nome, numero, data_entrada, data_oficio, data_nascimento, comprovante_pagamento, matricula_associacao) VALUES ('teste','teste','teste','teste','teste','teste','teste','teste')

CREATE TABLE local(
nomelocal varchar(255) primary key,
logradouro varchar(255),
piscina varchar(255));

CREATE TABLE competicao(
nome VARCHAR(255) primary key,
data VARCHAR(255),
nomelocal VARCHAR(255),
foreign key (nomelocal) references local(nomelocal));

CREATE TABLE prova(
nome VARCHAR(255) primary key,
categoria VARCHAR(255),
classe VARCHAR(255),
nome_competicao VARCHAR(255),
foreign key (nome_competicao) references competicao(nome) ON UPDATE CASCADE);


CREATE TABLE atletaprova(
nome_prova VARCHAR(255),
matricula_atleta VARCHAR(255),
ponto INT,
tempo VARCHAR(255),
primary key (matricula_atleta, nome_prova),
foreign key (nome_prova) references prova(nome));
insert into atletaprova(nome_prova, matricula_atleta, ponto, tempo) values('testeProva', 'teste', '26', '02:00.00');