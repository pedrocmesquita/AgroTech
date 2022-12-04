/*
Como Gestor Agrícola, quero gerir os meus clientes, empresas ou particulares, que
compram os bens produzidos na minha exploração agrícola.

Critério de Aceitação [BDDAD]
1. Um utilizador pode inserir um novo Cliente no Base de Dados, com os dados que descrevem um cliente, sem a necessidade de escrever código SQL. Se a inserção for bem-sucedida, o utilizador é informado sobre o valor da chave primária do novo cliente
2. Quando o processo de inserção falha, o utilizador é informado sobre o erro que pode ter
ocorrido.
*/
CREATE OR REPLACE PROCEDURE insert_client
(
    id_cliente cliente.id_cliente%TYPE,
    nome cliente.nome%TYPE,
    email cliente.email%TYPE,
    nif cliente.nif%TYPE,
    morada_correspondencia cliente.morada_correspondencia%TYPE,
    morada_entrega cliente.morada_entrega%TYPE,
    plafond cliente.plafond%TYPE,
    n_encomendas_ultimo_ano cliente.n_encomendas_ultimo_ano%TYPE,
    /* data_ultimo_incidente cliente.data_ultimo_incidente%TYPE, */
    data_ultimo_incidente VARCHAR2(255),
    n_incidente cliente.n_incidente%TYPE,
    valor_encomendas_ultimo_ano cliente.valor_encomendas_ultimo_ano%TYPE
)
IS
BEGIN TRY
    INSERT INTO cliente
	(
		id_cliente, nome, email, NIF, morada_correspondencia, morada_entrega, plafond, n_encomendas_ultimo_ano, data_ultimo_incidente, n_incidente, valor_encomendas_ultimo_ano
	)
	
	VALUES
	(
		id_cliente, nome, email, NIF, morada_correspondencia, morada_entrega, plafond, n_encomendas_ultimo_ano, TO_DATE(data_ultimo_incidente,'DD/MM/YY'), n_incidente, valor_encomendas_ultimo_ano
	)
	
    DBMS_OUTPUT.PUT_LINE('Cliente inserido com sucesso.\nID do cliente: ' || id_cliente);
END TRY
BEGIN CATCH
    DBMS_OUTPUT.PUT_LINE('Falha na inserção do cliente.');
END CATCH;
/