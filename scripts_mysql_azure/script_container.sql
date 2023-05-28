CREATE SCHEMA IF NOT EXISTS bd_analyx DEFAULT CHARACTER SET utf8 ;
USE bd_analyx;

CREATE TABLE IF NOT EXISTS cpu (
  id INT NOT NULL AUTO_INCREMENT,
  modeloCPU VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS disco (
  id INT NOT NULL AUTO_INCREMENT,
  volume BIGINT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS ram (
  id INT NOT NULL AUTO_INCREMENT,
  total BIGINT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS especificacaoMaquina (
  id INT NOT NULL AUTO_INCREMENT,
  hostName VARCHAR(100) NOT NULL,
  fkCpu INT NOT NULL,
  fkDisco INT NOT NULL,
  fkRam INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_especificacaoMaquina_cpu1
    FOREIGN KEY (fkcpu)
    REFERENCES cpu (id),
  CONSTRAINT fk_especificacaoMaquina_Disco1
    FOREIGN KEY (fkDisco)
    REFERENCES disco (id),
  CONSTRAINT fk_especificacaoMaquina_ram1
    FOREIGN KEY (fkRam)
    REFERENCES ram (id));

CREATE TABLE IF NOT EXISTS monitoramento (
  id INT NOT NULL AUTO_INCREMENT,
  data DATE NOT NULL,
  hora TIME NOT NULL,
  fkMaquina INT NOT NULL,
  CONSTRAINT fk_Monitoramento_EspecificacaoMaquina1
    FOREIGN KEY (fkMaquina)
    REFERENCES especificacaoMaquina (id),
  PRIMARY KEY (id, fkMaquina));

CREATE TABLE IF NOT EXISTS pacote (
  id INT NOT NULL AUTO_INCREMENT,
  latencia DECIMAL(5,2) NOT NULL,
  pacotesEnviados BIGINT NOT NULL,
  pacotesRecebidos BIGINT NOT NULL,
  bytesEnviados BIGINT NOT NULL,
  bytesRecebidos BIGINT NOT NULL,
  fkMonitoramento INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_rede_Monitoramento1
    FOREIGN KEY (fkMonitoramento)
    REFERENCES monitoramento (id));

CREATE TABLE IF NOT EXISTS tipoComponente (
  id INT NOT NULL AUTO_INCREMENT,
  tipoComponente VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS componente (
  id INT NOT NULL AUTO_INCREMENT,
  uso DECIMAL(5,2) NOT NULL,
  fkMonitoramento INT NOT NULL,
  fkTipoComponente INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_componente_Monitoramento1
    FOREIGN KEY (fkMonitoramento)
    REFERENCES monitoramento (id),
  CONSTRAINT fk_componente_table11
    FOREIGN KEY (fkTipoComponente)
    REFERENCES tipoComponente (id));

CREATE TABLE IF NOT EXISTS tipoCategoria (
  id INT NOT NULL AUTO_INCREMENT,
  tipoCategoria VARCHAR(45) NOT NULL,
  descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));
  
insert into tipoComponente values
(null,'cpu'),
(null,'disco'),
(null,'ram');
