CREATE TABLE fornecedores (
	cnpj VARCHAR(20) PRIMARY KEY,
	nome VARCHAR(100) NOT NULL
);

CREATE TABLE vacinas (
	nome VARCHAR(50) NOT NULL,
	lote VARCHAR(50) NOT NULL,
	validade DATE DEFAULT NULL,
	qtd_estoque INT NOT NULL,
	PRIMARY KEY (nome, lote)
);

CREATE TABLE vacinas_fornecedores (
	cod SERIAL PRIMARY KEY,
	qtde INT NOT NULL,
	data_compra DATE NOT NULL,
	data_validade DATE NOT NULL,
	valor_unit DECIMAL(6,2) NOT NULL,
	nome_vac VARCHAR(50) NOT NULL,
	lote_vac VARCHAR(50) NOT NULL,
	cnpj_fornecedor VARCHAR(20) NOT NULL,
	FOREIGN KEY (nome_vac, lote_vac) REFERENCES vacinas (nome, lote),
	FOREIGN KEY (cnpj_fornecedor) REFERENCES fornecedores(cnpj)
);

CREATE TABLE funcionarios (
	cod SERIAL PRIMARY KEY,
	crm VARCHAR(20),
	nome VARCHAR(100) NOT NULL,
	cpf CHAR(11) DEFAULT NULL
);

CREATE TABLE pacientes (
	cpf CHAR(11) PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100),
	telefone VARCHAR(20) NOT NULL,
	sexo CHAR(1) NOT NULL,
	data_nasc DATE NOT NULL
);


CREATE TABLE cartao_vacina (
	cod SERIAL PRIMARY KEY,
	n_dose INT DEFAULT 1,
	data_vacina DATE DEFAULT CURRENT_DATE,
	data_renovacao_vacina DATE NOT NULL,
	preco DECIMAL(6,2) NOT NULL,
	nome_vac VARCHAR(50) NOT NULL,
	lote_vac VARCHAR(50) NOT NULL,
	cpf_paciente CHAR(11) NOT NULL,
	cod_funcionario INT NOT NULL,
	FOREIGN KEY (nome_vac, lote_vac) REFERENCES vacinas(nome, lote),
	FOREIGN KEY (cpf_paciente) REFERENCES pacientes(cpf),
	FOREIGN KEY (cod_funcionario) REFERENCES funcionarios(cod)
);

---------------------------------------
-------------- TRIGGERS ---------------
---------------------------------------

-- CADASTRAR/ATUALIZAR VACINA (PRONTO)

CREATE TRIGGER cadastrar_atualizar_vacinas
BEFORE INSERT ON vacinas_fornecedores
FOR EACH ROW 
EXECUTE PROCEDURE cadastrar_atualizar_vacinas();

CREATE OR REPLACE FUNCTION cadastrar_atualizar_vacinas()
RETURNS TRIGGER AS $cadastrar_atualizar_vacinas$
BEGIN 
	IF (NEW.nome_vac NOT IN (SELECT nome FROM vacinas WHERE lote = NEW.lote_vac))
	THEN
		INSERT INTO vacinas (nome, lote, validade, qtd_estoque) VALUES (NEW.nome_vac, NEW.lote_vac, NEW.data_validade, NEW.qtde);
	ELSE
		UPDATE vacinas
		SET qtd_estoque = (qtd_estoque + NEW.qtde)
		WHERE NEW.lote_vac = lote AND NEW.nome_vac = nome;
	END IF;
	RETURN NEW;
END;
$cadastrar_atualizar_vacinas$ LANGUAGE PLPGSQL;

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

-- EVITAR APLICACAO DE VACINA VENCIDA -------------------
CREATE TRIGGER evitar_aplicacao_vacina
BEFORE INSERT ON cartao_vacina
FOR EACH ROW EXECUTE PROCEDURE evitar_aplicacao_vacina();

CREATE OR REPLACE FUNCTION evitar_aplicacao_vacina()
RETURNS TRIGGER AS $evitar_aplicacao_vacina$
BEGIN 
	IF ((SELECT validade FROM vacinas WHERE nome = NEW.nome_vac AND lote = NEW.lote_vac) < (SELECT CURRENT_DATE)) THEN
		RAISE EXCEPTION 'VACINA VENCIDA!!!';
	END IF;
	RETURN NEW;
END;

$evitar_aplicacao_vacina$ LANGUAGE PLPGSQL;

-- DMLs ------------------------------------------------------

select * from pacientes;
select * from funcionarios;
select * from cartao_vacina;
select * from vacinas;

-- FORNECEDORES
INSERT INTO fornecedores VALUES ('CNPJ1', 'Jefferson');
INSERT INTO fornecedores VALUES ('CNPJ2', 'Maria');
INSERT INTO fornecedores VALUES ('CNPJ3', 'Joao');
INSERT INTO fornecedores VALUES ('CNPJ5', 'Farmacia CIA');

-- PACIENTES
INSERT INTO pacientes VALUES ('03173871030', 'Joice Daiani', 'joice@gmail.com', '(51) 999995555', 'F', '1994-08-25');
INSERT INTO pacientes VALUES ('11122233344', 'Junior Fonseca', 'junior@gmail.com', '(51) 888888888', 'M', '1999-12-10');
INSERT INTO pacientes VALUES ('22200022200', 'Maiara Cananda', 'maiara@gmail.com', '(51) 777777777', 'F', '2000-02-14');

-- COMPRA DE VACINAS
INSERT INTO vacinas_fornecedores (qtde, data_compra, data_validade, valor_unit, nome_vac, lote_vac, cnpj_fornecedor) VALUES (20, '2017-06-23', '2017-12-23', 22.55, 'Vacina 01', 'LOTE3', 'CNPJ1');
INSERT INTO vacinas_fornecedores (qtde, data_compra, data_validade, valor_unit, nome_vac, lote_vac, cnpj_fornecedor) VALUES (30, '2017-06-24', '2018-12-23', 22.55, 'Vacina 01', 'LOTE3', 'CNPJ1');
INSERT INTO vacinas_fornecedores (qtde, data_compra, data_validade, valor_unit, nome_vac, lote_vac, cnpj_fornecedor) VALUES (50, '2017-06-23', '2018-08-01', 15.90, 'Vacina da Gripe', 'LOTEABC', 'CNPJ3');

-- FUNCIONARIOS
INSERT INTO funcionarios (crm, nome, cpf) VALUES ('CRM1', 'Josefino', '09876543210');
INSERT INTO funcionarios (crm, nome, cpf) VALUES ('CRM2', 'Zulmira', '12345678900');
INSERT INTO funcionarios (crm, nome, cpf) VALUES (DEFAULT, 'Joaozinho', '64868498845');

-- CARTAO VACINA

INSERT INTO cartao_vacina (n_dose, data_renovacao_vacina, preco, nome_vac, lote_vac, cpf_paciente, cod_funcionario) VALUES
(1, '23-06-2018', 2.99, 'Vacina 01', 'LOTE1', '03173871030', 1);

select * from cartao_vacina;

