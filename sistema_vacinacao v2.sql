CREATE TABLE IF NOT EXISTS fornecedores (
	cnpj VARCHAR(20) PRIMARY KEY,
	nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS vacinas (
	nome VARCHAR(50) NOT NULL,
	lote VARCHAR(50) NOT NULL,
	validade DATE DEFAULT NULL,
	qtd_estoque INT NOT NULL,
	PRIMARY KEY (nome, lote)
);

CREATE TABLE IF NOT EXISTS vacinas_fornecedores (
	cod SERIAL PRIMARY KEY,
	qtde INT NOT NULL,
	data DATE NOT NULL,
	valor_unit DECIMAL(6,2) NOT NULL,
	nome_vac VARCHAR(50) NOT NULL,
	lote_vac VARCHAR(50) NOT NULL,
	cnpj_fornecedor VARCHAR(20) NOT NULL,
	FOREIGN KEY (nome_vac, lote_vac) REFERENCES vacinas (nome, lote),
	FOREIGN KEY (cnpj_fornecedor) REFERENCES fornecedores(cnpj)
);

CREATE TABLE IF NOT EXISTS funcionarios (
	cod SERIAL PRIMARY KEY,
	crm VARCHAR(20),
	nome VARCHAR(100) NOT NULL,
	cpf CHAR(11) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS pacientes (
	cpf CHAR(11) PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100),
	telefone VARCHAR(20) NOT NULL,
	sexo CHAR(1) NOT NULL,
	data_nasc DATE NOT NULL
);


CREATE TABLE IF NOT EXISTS cartao_vacina (
	cod SERIAL PRIMARY KEY,
	n_dose INT DEFAULT 1,
	data DATE NOT NULL,
	preco DECIMAL(6,2) NOT NULL,
	nome_vac VARCHAR(50) NOT NULL,
	lote_vac VARCHAR(50) NOT NULL,
	cpf_paciente CHAR(11) NOT NULL,
	cod_funcionario INT NOT NULL,
	FOREIGN KEY (nome_vac, lote_vac) REFERENCES vacinas(nome, lote),
	FOREIGN KEY (cpf_paciente) REFERENCES pacientes(cpf),
	FOREIGN KEY (cod_funcionario) REFERENCES funcionarios(cod)
);

-------------- TRIGGERS ---------------

-- ATUALIZAR ESTOQUE (aumentar quando comprar do fornecedor)

CREATE TRIGGER aumentar_estoque_vacinas
BEFORE INSERT ON vacinas_fornecedores
FOR EACH ROW EXECUTE PROCEDURE aumentar_estoque_vacinas();

CREATE OR REPLACE FUNCTION aumentar_estoque_vacinas()
RETURNS TRIGGER AS $aumentar_estoque_vacinas$
BEGIN 
	IF (TRUE) THEN
		UPDATE vacinas
		SET qtd_estoque = (qtd_estoque + NEW.qtde)
		WHERE NEW.lote_vac = lote AND NEW.nome_vac = nome;
	END IF;
	RETURN NEW;
END;
$aumentar_estoque_vacinas$ LANGUAGE PLPGSQL;


-- TIRAR DO ESTOQUE (tirar quando faz uma vacina)

CREATE TRIGGER diminuir_estoque_vacinas
AFTER INSERT ON cartao_vacina
FOR EACH ROW EXECUTE PROCEDURE diminuir_estoque_vacinas();

CREATE OR REPLACE FUNCTION diminuir_estoque_vacinas()
RETURNS TRIGGER AS $diminuir_estoque_vacinas$
BEGIN 
	IF (TRUE) THEN
		UPDATE vacinas
		SET qtd_estoque = qtd_estoque - 1
		WHERE NEW.lote_vac = lote AND NEW.nome_vac = nome;
	END IF;
	RETURN NEW;
END;
$diminuir_estoque_vacinas$ LANGUAGE PLPGSQL;

-- CADASTRAR VACINA AINDA NAO CADASTRADA (cadastro é feita uma compra sem que haja cadastro em VACINAS)

CREATE TRIGGER cadastrar_vacinas
BEFORE INSERT ON vacinas_fornecedores
FOR EACH ROW 
EXECUTE PROCEDURE cadastrar_vacinas();

CREATE OR REPLACE FUNCTION cadastrar_vacinas()
RETURNS TRIGGER AS $cadastrar_vacinas$
BEGIN 
	IF (NEW.nome_vac NOT IN (SELECT nome FROM vacinas WHERE lote = NEW.lote_vac))
	THEN
		INSERT INTO vacinas (nome, lote, validade, qtd_estoque) VALUES (NEW.nome_vac, NEW.lote_vac, NULL, NEW.qtde);
	END IF;
	RETURN NEW;
END;
$cadastrar_vacinas$ LANGUAGE PLPGSQL;