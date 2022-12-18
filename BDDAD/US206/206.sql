/*
Como Gestor Agrícola, quero manter a estrutura da minha exploração agrícola – contendo
um conjunto de Setores – atualizada, ou seja, quero especificar cada um dos Setores. As suas características, como tipo de cultivo e cultivo, devem ser configuradas.
*/

/*
1. Um utilizador pode criar Setores numa exploração agrícola biológica especificando suas
características.
*/
CREATE OR REPLACE PROCEDURE insert_setor
(
	designacao parcela.designacao%TYPE,
	id_quinta parcela.id_quinta%TYPE,
	numero_colheita parcela.numero_colheita%TYPE,
	area parcela.area%TYPE,
)
IS
BEGIN
BEGIN TRY
    INSERT INTO parcela
	(
		designacao, id_quinta, numero_colheita, area
	)
	
	VALUES
	(
		designacao, id_quinta, numero_colheita, area
	);
	
    DBMS_OUTPUT.PUT_LINE('Setor inserido com sucesso.\nDesignação do Setor: ' || designacao);
END TRY
BEGIN CATCH
    DBMS_OUTPUT.PUT_LINE('Falha na inserção do Setor.');
END CATCH
END;
/

/*
2. É possível definir novos tipos de características parametrizadas, como tipo de cultura ou cultura entre outras.
*/
CREATE OR REPLACE PROCEDURE insert_cultura
(
	id_cultura cultura.id_cultura%TYPE,
	tipo cultura.tipo%TYPE,
	objetivo cultura.objetivo%TYPE,
)
IS
BEGIN
BEGIN TRY
	INSERT INTO cultura
	(
		id_cultura, tipo, objetivo
	)
	
	VALUES
	(
		id_cultura, tipo, objetivo
	);
	
    DBMS_OUTPUT.PUT_LINE('Cultura inserida com sucesso.\nID da cultura: ' || id_cultura);
END TRY
BEGIN CATCH
    DBMS_OUTPUT.PUT_LINE('Falha na inserção da cultura.');
END CATCH
END;
/

/*
3. Um utilizador pode listar os Setores de sua exploração agrícola ordenados por ordem
alfabética.
*/
CREATE OR REPLACE PROCEDURE list_setor_by_name()
IS
BEGIN
	Select * FROM parcela
	ORDER BY designacao ASC;
END;
/

/*
4. Um utilizador pode listar os Setores de sua exploração agrícola ordenados por tamanho, em ordem crescente ou decrescente.
*/
CREATE OR REPLACE PROCEDURE list_setor_by_size_asc()
IS
BEGIN
	Select * FROM parcela
	ORDER BY area ASC;
END;
/

CREATE OR REPLACE PROCEDURE list_setor_by_size_dsc()
IS
BEGIN
	Select * FROM parcela
	ORDER BY area DESC;
END;
/

/*
5. Um utilizador pode listar os Setores de sua exploração agrícola ordenados por tipo de cultura e cultura.
*/
CREATE OR REPLACE PROCEDURE list_setor_by_cultura()
IS
BEGIN
	Select * FROM cultura
	ORDER BY tipo ASC, id_cultura ASC;
END;
/