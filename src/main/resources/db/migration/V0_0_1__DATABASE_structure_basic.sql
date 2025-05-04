CREATE TABLE tb_consultor (
	id_consultor INTEGER  AUTO_INCREMENT NOT NULL,
	nome_consultor varchar(250) NOT NULL,
	cpf_consultor varchar(15) NOT NULL,
	rg_consultor varchar(15) NOT NULL,
	endereco_consultor varchar(500) NULL,
	CONSTRAINT tb_consultor_pk PRIMARY KEY (id_consultor)
);

INSERT INTO tb_consultor
(nome_consultor, cpf_consultor, rg_consultor, endereco_consultor)
VALUES('FERNANDO TEIXERIA VIEIRA', '57589488291', '2195003-SSP-PA', 'AV. GOV. MAGALHÃES BARATA, 92 - NAZARÉ - BELÉM - PA - 66040170');

select * from tb_consultor;

CREATE TABLE tb_cliente (
	id_cliente INTEGER  AUTO_INCREMENT NOT NULL,
	razao_social_cliente varchar(250) NOT NULL,
	nome_fantasia_cliente varchar(250) NOT NULL,
	cnpj_cliente varchar(15) NOT NULL,
	endereco_cliente varchar(500) NULL,
	CONSTRAINT tb_cliente_pk PRIMARY KEY (id_cliente)
);

INSERT INTO tb_cliente
(razao_social_cliente, nome_fantasia_cliente, cnpj_cliente, endereco_cliente)
VALUES('F T Vieira Tecnologia da Informacao', 'Abstract Tech', '40508893000103', 'AV. GOV. MAGALHÃES BARATA, 92 - NAZARÉ - BELÉM - PA - 66040170');
select * from tb_cliente;

CREATE TABLE tb_atividade (
	id_atividade INTEGER  AUTO_INCREMENT NOT NULL,
	descricao_atividade varchar(500) NOT NULL,
	ticket_atividade varchar(25) NOT NULL,
	status_atividade varchar(25) NOT NULL,
	CONSTRAINT tb_atividade_pk PRIMARY KEY (id_atividade)
);
INSERT INTO tb_atividade
(descricao_atividade, ticket_atividade, status_atividade)
VALUES('DAILY', '00000100', 'ATIVA');
INSERT INTO tb_atividade
(descricao_atividade, ticket_atividade, status_atividade)
VALUES('DESENVOLVIMENTO', '00000101', 'ATIVA');
INSERT INTO tb_atividade
(descricao_atividade, ticket_atividade, status_atividade)
VALUES('BANCO DE DADOS', '00000102', 'ATIVA');
INSERT INTO tb_atividade
(descricao_atividade, ticket_atividade, status_atividade)
VALUES('TESTES DE SOFTWARE', '00000103', 'ATIVA');
INSERT INTO tb_atividade
(descricao_atividade, ticket_atividade, status_atividade)
VALUES('LEVANTAMENTO DE REQUISITOS', '00000104', 'ATIVA');
select * from tb_atividade;

CREATE TABLE tb_ponto (
	id_ponto INTEGER  AUTO_INCREMENT NOT NULL,
	data_ponto date NOT NULL,
	dia_ponto varchar(25) NOT NULL,
	inicio_ponto time NULL,
	final_ponto time NULL,
	total_hora_ponto time NULL,
	status__ponto varchar(25) NOT NULL,
	ticket_ponto varchar(25) NOT NULL,
	id_consultor INTEGER NOT NULL,
	id_cliente INTEGER NOT NULL,
	id_atividade INTEGER NOT NULL,
	CONSTRAINT tb_ponto_pk PRIMARY KEY (id_ponto)
);

ALTER TABLE tb_ponto ADD CONSTRAINT tb_ponto_tb_consultor_fk FOREIGN KEY (id_consultor) REFERENCES tb_consultor(id_consultor);
ALTER TABLE tb_ponto ADD CONSTRAINT tb_ponto_tb_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id_cliente);
ALTER TABLE tb_ponto ADD CONSTRAINT tb_ponto_tb_atividade_fk FOREIGN KEY (id_atividade) REFERENCES tb_atividade(id_atividade);

INSERT INTO tb_ponto
(data_ponto, dia_ponto, inicio_ponto, final_ponto, total_hora_ponto, status__ponto, ticket_ponto, id_consultor, id_cliente, id_atividade)
VALUES('2025-04-25', 'SEXTA-FEIRA', '09:00:00', '12:05:00', '03:00:00', 'ATIVO', '00000200', 1, 1, 1);

INSERT INTO tb_ponto
(data_ponto, dia_ponto, inicio_ponto, final_ponto, total_hora_ponto, status__ponto, ticket_ponto, id_consultor, id_cliente, id_atividade)
VALUES('2025-04-25', 'SEXTA-FEIRA', '13:00:00', '18:00:00', (SELECT TIMEDIFF('18:00:00', '13:00:00')), 'ATIVO', '00000201', 1, 1, 2);

select * from tb_ponto;