CREATE TABLE empresa (
  id INT PRIMARY KEY IDENTITY(1,1),
  cnpj CHAR(17) NULL,
  razaoSocial VARCHAR(128) NULL,
  responsavel VARCHAR(45) NULL,
  telefone CHAR(13) NULL,
  email VARCHAR(256) NULL,
);

CREATE TABLE cpu (
  id INT PRIMARY KEY IDENTITY(1,1),
  modeloCPU VARCHAR(45) NULL,
);

CREATE TABLE disco (
  id INT PRIMARY KEY IDENTITY(1,1),
  volume VARCHAR(45) NULL,
);

CREATE TABLE ram (
  id INT PRIMARY KEY IDENTITY(1,1),
  total VARCHAR(45) NULL,
);

CREATE TABLE especificacaoMaquina (
  id INT PRIMARY KEY IDENTITY(1,1),
  numeroSerial VARCHAR(45) NULL,
  cpu INT NOT NULL,
  disco INT NOT NULL,
  ram INT NOT NULL,
  CONSTRAINT fk_especificacaoMaquina_cpu1
    FOREIGN KEY (cpu)
    REFERENCES cpu (id),
  CONSTRAINT fk_especificacaoMaquina_Disco1
    FOREIGN KEY (disco)
    REFERENCES disco (id),
  CONSTRAINT fk_especificacaoMaquina_ram1
    FOREIGN KEY (ram)
    REFERENCES ram (id));

CREATE TABLE setor (
  id INT PRIMARY KEY IDENTITY(1,1),
  nome VARCHAR(45) NULL,
);

CREATE TABLE funcionario (
  id INT PRIMARY KEY IDENTITY(1,1),
  nome VARCHAR(45) NULL,
  matricula VARCHAR(45) NULL UNIQUE,
  empresa INT NULL,
  gestor INT NULL,
  maquina INT NULL,
  setor INT NULL,
  CONSTRAINT fk_Funcionario_Empresa1
    FOREIGN KEY (empresa)
    REFERENCES empresa (id),
  CONSTRAINT fk_Funcionario_Funcionario1
    FOREIGN KEY (gestor)
    REFERENCES funcionario (id),
  CONSTRAINT fk_Funcionario_EspecificacaoMaquina1
    FOREIGN KEY (maquina)
    REFERENCES especificacaoMaquina (id),
  CONSTRAINT fk_Funcionario_setor1
    FOREIGN KEY (setor)
    REFERENCES setor (id)
);

CREATE TABLE tipoUsuario (
  id INT PRIMARY KEY IDENTITY(1,1),
  tipoUsuario VARCHAR(45) NULL,
);

CREATE TABLE usuario (
  id INT PRIMARY KEY IDENTITY(1,1),
  email VARCHAR(256) NULL UNIQUE,
  senha VARCHAR(256) NULL,
  tipo INT NOT NULL,
  funcionario INT NOT NULL,
  CONSTRAINT fk_Usuario_Funcionario1
    FOREIGN KEY (funcionario)
    REFERENCES funcionario (id),
  CONSTRAINT fk_Usuario_tipoUsuario1
    FOREIGN KEY (tipo)
    REFERENCES tipoUsuario (id)
);

CREATE TABLE monitoramento (
  id INT PRIMARY KEY IDENTITY(1,1),
  data DATE NULL,
  maquina INT NOT NULL,
  CONSTRAINT fk_Monitoramento_EspecificacaoMaquina1
    FOREIGN KEY (maquina)
    REFERENCES especificacaoMaquina (id)
);

CREATE TABLE rede (
  id INT PRIMARY KEY IDENTITY(1,1),
  latencia INT NULL,
  upload INT NULL,
  download INT NULL,
  monitoramento INT NOT NULL,
  CONSTRAINT fk_rede_Monitoramento1
    FOREIGN KEY (monitoramento)
    REFERENCES monitoramento (id)
);

CREATE TABLE tipoComponente (
  id INT PRIMARY KEY IDENTITY(1,1),
  tipoComponente VARCHAR(45) NULL,
);

CREATE TABLE componente (
  id INT PRIMARY KEY IDENTITY(1,1),
  uso FLOAT NULL,
  monitoramento INT NOT NULL,
  tipoComponente INT NOT NULL,
  CONSTRAINT fk_componente_Monitoramento1
    FOREIGN KEY (monitoramento)
    REFERENCES monitoramento (id),
  CONSTRAINT fk_componente_table11
    FOREIGN KEY (tipoComponente)
    REFERENCES tipoComponente (id)
);

CREATE TABLE tipoCategoria (
  id INT PRIMARY KEY IDENTITY(1,1),
  tipoCategoria VARCHAR(45) NULL,
  descricao VARCHAR(45) NULL,
);

CREATE TABLE alertas (
  id INT PRIMARY KEY IDENTITY(1,1),
  nivelGravidade VARCHAR(45) NULL,
  monitoramento INT NOT NULL,
  tipoComponente INT NOT NULL,
  tipoCategoria INT NOT NULL,
  CONSTRAINT fk_Alertas_Monitoramento1
    FOREIGN KEY (monitoramento)
    REFERENCES monitoramento (id),
  CONSTRAINT fk_Alertas_table11
    FOREIGN KEY (tipoComponente)
    REFERENCES tipoComponente (id),
  CONSTRAINT fk_Alertas_tipoCategoria1
    FOREIGN KEY (tipoCategoria)
    REFERENCES tipoCategoria (id)
);

CREATE TABLE log (
  id INT PRIMARY KEY IDENTITY(1,1),
  descrição VARCHAR(45) NULL,
  componente INT NOT NULL,
  CONSTRAINT fk_Log_componente1
    FOREIGN KEY (componente)
    REFERENCES componente (id)
);
    
insert into tipousuario values
('web'),
('java');

insert into setor values
('DevOps');

insert into empresa values
('00000000000100', 'SPTech School','Rafel Petry','(11)959595959','rafael.petry@sptech.school');

insert into cpu values
('i5');

insert into disco values
('1tb');

insert into ram values
('8bg');

insert into especificacaoMaquina values
('06515KLN51',1,1,1);

insert into funcionario values
('Marcio','01191000',1,null,null,1);

insert into funcionario values
('João','01202000',1,1,null,1);

insert into usuario values
('marcio@email.com','1234',1,1),
('teste@email.com','1234',2,2);

insert into monitoramento values
('2023-04-23',1);

insert into tipoComponente values
('cpu'),
('disco'),
('ram');

insert into componente values
(50,1,1),
(70,1,2),
(30,1,3);
select * from componente;

select c.uso,c.tipoComponente,tc.tipoComponente 
				from componente as c
                join tipoComponente as tc
                on tc.id = c.tipoComponente;

select u.id,
		u.email,
		u.senha,
		tu.tipoUsuario,
		f.nome from usuario as u
			join tipousuario as tu
			on tu.id = u.tipo
				join funcionario as f
				on f.id = u.funcionario;

select u.id,
		u.email,
		u.senha,
		tu.tipoUsuario,
		f.nome as funcionario from usuario as u
		join tipousuario as tu
		on tu.id = u.tipo
				join funcionario as f
				on f.id = u.funcionario where email = 'teste@email.com' and senha = '1234';