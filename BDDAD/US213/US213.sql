CREATE OR REPLACE TRIGGER trigger_pistas_auditoria
    AFTER INSERT OR UPDATE OR DELETE on operacao
    FOR EACH ROW
declare
    id_operacao operacao.id_operacao%Type;
    id_parcela operacao.id_parcela%Type;
    tipo_alteracao VARCHAR(10);
BEGIN
    id_operacao := :new.id_operacao;


    IF INSERTING THEN
        tipo_alteracao := 'insert';

        id_parcela := :new.id_parcela;
    ELSIF UPDATING THEN
        tipo_alteracao := 'update';

        id_parcela := :new.id_parcela;
    ELSIF DELETING THEN
        tipo_alteracao := 'delete';

        id_parcela := :old.id_parcela;
    END IF;

    INSERT INTO pistas_auditoria(id_parcela, utilizador, data_tempo_operacao, tipo_alteracao)
    VALUES (id_setor, user, SYSDATE, tipo_alteracao);
END;
/



-------- Inserts, Updates and Deletes

DECLARE
    id_operacao_agricola_inserted operacao.id_operacao%TYPE;
BEGIN
    Insert into operacao (id_parcela,tipo,data_operacao,estado) values ('1','irrigação',to_date('22.02.27','RR.MM.DD'),'agendada') returning ID_OPERACAO into id_operacao_agricola_inserted;
    UPDATE operacao SET estado_operacao = 'cancelada' WHERE id_operacao = id_operacao_agricola_inserted;
    DELETE operacao WHERE id_operacao = id_operacao_agricola_inserted;
END;
/

DECLARE
    id_operacao_agricola_inserted operacao.id_operacao%TYPE;
BEGIN
    Insert into operacao (id_parcela,tipo,data_operacao,estado) values ('2','irrigação',to_date('20.09.27','RR.MM.DD'),'cancelada') returning ID_OPERACAO into id_operacao_agricola_inserted;
    UPDATE operacao SET estado_operacao = 'agendada' WHERE id_operacao = id_operacao_agricola_inserted;
    DELETE operacao WHERE id_operacao = id_operacao_agricola_inserted;
END;
/

DECLARE
    id_operacao_agricola_inserted operacao.id_operacao%TYPE;
BEGIN
    Insert into operacao (id_parcela,tipo,data_operacao,estado) values ('3','irrigação',to_date('21.01.23','RR.MM.DD'),'cancelada') returning ID_OPERACAO into id_operacao_agricola_inserted;
    UPDATE operacao SET estado_operacao = 'agendada' WHERE id_operacao = id_operacao_agricola_inserted;
    DELETE operacao WHERE id_operacao = id_operacao_agricola_inserted;
END;
/


------- Criar as views para confirmar resultados
CREATE VIEW allOperacoesAgricolas AS
SELECT * FROM pistas_auditoria
ORDER BY data_tempo_operacao ASC;

CREATE VIEW OperacoesAgricolaInsert AS
SELECT * FROM pistas_auditoria
WHERE tipo_alteracao='insert'
ORDER BY data_tempo_operacao ASC;

CREATE VIEW OperacoesAgricolaUpdate AS
SELECT * FROM pistas_auditoria
WHERE tipo_alteracao='update'
ORDER BY data_tempo_operacao ASC;

CREATE VIEW OperacoesAgricolaDelete AS
SELECT * FROM pistas_auditoria
WHERE tipo_alteracao='delete'
ORDER BY data_tempo_operacao ASC;

--------- Tabela pistas_auditoria stats
SELECT * FROM allOperacoesAgricolas;
SELECT COUNT(*) AS "Numero Operacoes" FROM allOperacoesAgricolas;

SELECT * FROM OperacoesAgricolaInsert;
SELECT COUNT(*)  AS "Numero Inserts" FROM OperacoesAgricolaInsert;

SELECT * FROM OperacoesAgricolaUpdate;
SELECT COUNT(*)  AS "Numero Updates" FROM OperacoesAgricolaUpdate;

SELECT * FROM OperacoesAgricolaDelete;
SELECT COUNT(*)  AS "Numero Deletes" FROM OperacoesAgricolaDelete;