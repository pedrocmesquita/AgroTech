CREATE OR REPLACE PROCEDURE ler_inputs_sensores
AS
CURSOR inputs_sensores IS SELECT input_string From InputSensor;
input InputSensor.input_string%type;
    input_id_sensor Sensor.id_sensor%type;
    input_tipoSensor Sensor.tipo%type;
    input_valor_rido dados_sensor.valor_rtype;
    input_valor_referencia Sensor.valor_rref%type;
    input_instanteLeitura dados_sensores.data%type;
    dummy sensor.id_sensor%type;
    dummy2 sensor.valor_rref%type;

    inc_erros INTEGER;registos_c_erros INTEGER;registos_s_erros INTEGER;
    inc_registos INTEGER;
    data_tempo_registos DATE;

BEGIregistos_sem_erros := 0;
inc_registos := 0;
SELECT TO_DATE(SYSDATE, 'YYYY-MM-DD HH24') INTO data_tempo_registos FROM Dual;
OPEN inputs_sensores;
LOOP
FETCH inputs_sensores INTO input;
    EXIT WHEN inputs_sensores%NOTFOUND;

    inc_registos := inc_registos + 1;

    -- validate string size
    if LENGTH(input) = 25 then
        inc_erros := 0;

        input_id_sensor := SUBSTR(input, 1, 5);
        -- validate id_sensor
SELECT COUNT(*) INTO dummy FROM Sensor WHERE id_sensor = input_id_sensor;
if dummy > 0 then
            inc_erros := inc_erros + 1;
            DBMS_OUTPUT.put_line('Erro "Id sensor já registado" no ' || inc_registos || 'º registo lido');
end if;

        input_tipoSensor := SUBSTR(input, 6, 2);
        -- validate sensor type
        if input_tipoSensor != 'HS' AND input_tipoSensor != 'Pl' AND input_tipoSensor != 'TS' AND input_tipoSensor != 'VV' AND input_tipoSensor != 'TA' AND input_tipoSensor != 'HA' AND input_tipoSensor != 'PA' THEN
            inc_erros := inc_erros + 1;
            DBMS_OUTPUT.put_line('Erro "Tipo de sensor inválido" no ' || inc_registos || 'º registo lido');
end if;

        -- check if its int to avoid errors
        if REGEXP_LIKE(SUBSTR(input, 8, 3), '^[0-9]+$') then
            input_valor_rido := SUBSTR(input, 8,3);
            -- validate sensor value
            if input_valor_rido > 100 then
                inc_erros := inc_erros + 1;
                DBMS_OUTPUT.put_line('Erro "Valor_rlido inválido" no ' || inc_registos || 'º registo lido');
end if;
else
            inc_erros := inc_erros + 1;
            DBMS_OUTPUT.put_line('Erro "Valor_rlido não é um numero inteiro" no ' || inc_registos || 'º registo lido');
end if;

        -- check if its int to avoid errors
        if REGEXP_LIKE(SUBSTR(input, 11, 10), '^[0-9]+$') then
            input_valor_referencia := SUBSTR(input, 11, 10);
            -- validate sensor reference value
SELECT COUNT(*) INTO dummy FROM Sensor WHERE valor_ref = input_valor_ref;
if dummy > 0 then
                inc_erros := inc_erros + 1;
                DBMS_OUTPUT.put_line('Erro "Valor_rreferencia de sensor já registado" no ' || inc_registos || 'º registo lido');
end if;
else
            inc_erros := inc_erros + 1;
            DBMS_OUTPUT.put_line('Erro "Valor_rde referencia inválido" no ' || inc_registos || 'º registo lido');
end if;

        -- check if hours and numbers are ints in order to avoid errors

        if REGEXP_LIKE(SUBSTR(input, 21, 2), '^[0-9]+$') and REGEXP_LIKE(SUBSTR(input, 24, 2), '^[0-9]+$') and SUBSTR(input, 23, 1) = ':' then
            input_instanteLeitura := SUBSTR(input, 21,5);

            if SUBSTR(input_instanteLeitura, 1, 2) not between 0 and 24 OR SUBSTR(input_instanteLeitura, 4, 2) not between 0 and 60 then
                inc_erros := inc_erros + 1;
                DBMS_OUTPUT.put_line('Erro "Valor_rs para horas:minutos inválidos" no ' || inc_registos || 'º registo lido');
end if;
else
            inc_erros := inc_erros + 1;
            DBMS_OUTPUT.put_line('Erro "horas e minutos no formato errado" no ' || inc_registos || 'º registo lido');
end if;


else
        DBMS_OUTPUT.put_line('Erro "numero de caracteres inválido" no ' || inc_registos || 'º registo lido');
        inc_erros := inc_erros + 1;
end if;
    if inc_erros = 0 then
        INSERT INTO Sensor(id_sensor, tipo, valor_rido, valor_ref, instanteLeitura) VALUES (input_id_sensor, input_tipoSensor, input_valor_rido, input_valor_ref, input_data;
INSERT INTO RegistoLeituraSensores(data) VALUES (data_tempo_registos);
DELETE FROM InputSensor WHERE input_string = inputregistos_sem_erros :registos_sem_erros + 1;
ELSE
registos_com_erros :registos_com_erros + 1;

INSERT INTO RegistoLeituraSensores(dataLeitura, id_sensor, qtdErrosSensor) VALUES (data_tempo_registos, input_id_sensor, inc_erros);
end if;

END LOOP;

UPDATE RegistoLeituraSensores SET qtdRegistosLidos = inc_registos, qtdRegistosSemErro =registos_sem_erros, qtdRegistosErro = inc_registos registos_sem_erros
WHERE dataLeitura = data_tempo_registos;

CLOSE inputs_sensores;

DBMS_OUTPUT.put_line('Número de registos lidos: ' || inc_registos);
DBMS_OUTPUT.put_line('Número de registos transferidos lidos sem erros:' |registos_sem_erros);
DBMS_OUTPUT.put_line('Número de registos lidos com erros:' || TO_CHAR(inc_registos registos_sem_erros));
END;
/