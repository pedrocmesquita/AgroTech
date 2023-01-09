#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include "arqcp.h"
#include "asm.h"
#include "us101.h"
#include "us102.h"
#include "us103.h"
#define MAX_LINHAS 100
#define MAX_NUM_LINHAS 256

char n_sensor = 0;

int main(){


// ===========================================================================================================================


    if (!initialize())	//initialization successful
    {

        char tmp = pcg32_random_r() % 61 - 20;					//[0, 60] - 20 = [-20, 40] (ºC)
        unsigned char wind_vel = pcg32_random_r() % 51;			//[0, 50] (km/h)
        unsigned short wind_dir = pcg32_random_r() % 360;		//[0, 359] (º)
        unsigned char humd_atm = pcg32_random_r() % 101;		//[0, 100] (%)
        unsigned char humd_grnd = pcg32_random_r() % 101;		//[0, 100] (%)
        unsigned char rain = pcg32_random_r() % 26;				//[0,25] (mm)


        char rand = (char) ((pcg32_random_r() % 14) - 7);
        tmp = sens_temp(tmp, rand);

        rand = (char) ((pcg32_random_r() % 51) - 25);
        wind_vel = sens_velc_vento(wind_vel, rand);

        rand = (short) pcg32_random_r();
        wind_dir = sens_dir_vento(wind_dir, rand);

        rand = (char) pcg32_random_r();
        humd_atm = sens_humd_atm(humd_atm, rain, rand);

        rand = (char) pcg32_random_r();
        humd_grnd = sens_humd_solo(humd_grnd, rain, rand);

        rand = (char) ((pcg32_random_r() % 14) - 7);
        rain = sens_pluvio(rain, tmp, rand);


        printf("\n----------------------------\n");
        printf("Temperatura: %dºC\n",tmp);		//%d because %c converts to ascii
        printf("Velocidade do vento: %ukm/h\n",wind_vel);	//%u for unsigned
        printf("Direção do vento: %huº\n",wind_dir);	//short
        printf("Humidade atmosférica: %u%%\n",humd_atm);
        printf("Humidade do solo: %u%%\n",humd_grnd);
        printf("Pluviosidade: %umm\n",rain);
        printf("----------------------------\n");
    }

    else
    {
        printf("An error occured initializing the random number generator.\n");
    }



// ===========================================================================================================================
    int opc;
    char tentativas = 3, periodo = 20;
    float matrix[6][3];

    for (int i = 0; i < 6; ++i) {
        for (int j = 0; j < 3; ++j) {
            matrix[i][j] = 0;
        }
    }



    printf("Bem vindo\nEscolha uma das seguintes:\n1) Inserir sensor \n2) Matriz diaria de resumo dos sensores\n3)Exportar para ficheiro CSV\n");
    scanf("\n%d", &opc);


    switch (opc) {
        case 1:
            printf("\n\n");
            printf(">>>>>>>>>> INSERIR SENSOR <<<<<<<<<<\n");
            printf("Quantos sensores deseja inserir?\n");
            int n_sensor, i = 0, j = i + 1;
            float valor, max, min, freq = 0;
            scanf("%d", &n_sensor);

            Sensor *sensores = malloc(n_sensor * sizeof(Sensor));

            if (sensores == NULL) {
                fprintf(stderr, "Erro: Alocacao de memoria\n");
                return 0;
            }
            /* =================================== INSERIR DADOS CONFORME O RESTO DAS USs =================================================== */



            while (n_sensor != 0){
                printf("\n\n<<< SENSOR %d >>>\n", j );
                int opc_sensor;
                printf("\n-> Selecione o tipo do Sensor:\n1- Temperatura\n2- Velocidade do Vento\n3- Direcao do Vento\n4- Humidade Atmosferica\n5- Humidade Solo\n6- Pluviosidade\n");
                scanf("%d",&opc_sensor);

                while (opc_sensor < 1 || opc_sensor > 6){
                    printf("Opcao indisponivel. Tente novamente.\n");
                    scanf("%d", &opc_sensor);
                }

                // Array Leituras
                // matriz_diaria(); -------------------------------------------------> IMPORTANTE !!!!
                matriz_diaria(periodo, &sensores[i].id, matrix, opc_sensor - 1, 1000, 0);

                printf("Valor Limite Minimo:\n");
                scanf("%f", &min);

                printf("Valor Limite Maximo\n");
                scanf("%f", &max);

                while (max - min < 0){
                    printf("Valores maximo e minimo nao sao compativeis. Tente novamente\n");
                    printf("Valor Limite Minimo:\n");
                    scanf("%f", &min);

                    printf("Valor Limite Maximo\n");
                    scanf("%f", &max);
                }

                valor = *(*(matrix + opc_sensor - 1) + 2);

                // maximo e minimo
                if(escolher_max_min(max, min, valor,tentativas, sensores) == 1) {
                    //frequencia
                    if (escolher_frequencia(freq) == 1) {

                        // Tipo sensor
                        sensores[i].sensor_type = opc_sensor;

                        // ID SENSOR -- 4 digitos
                        sensores[i].id = 1000 + rand() % 9999;

                        // Maximo e Minimo
                        sensores[i].max_limit = max;
                        sensores[i].min_limit = min;

                        // Frequencia
                        sensores[i].frequency = freq;
                        sensores[i].readings_size = sensores[i].frequency;

                        printf("ID SENSOR: %d", sensores[i].id);
                        printf("\nRegisto concluido com sucesso!\n\n");
                    } else {
                        printf("Ocorreu um erro durante o registo. Saindo...\n");
                        break;
                    }
                } else {
                    printf("Ocorreu um erro durante o registo. Saindo...\n");
                    break;
                }
                n_sensor--;
                i++;
                j++;
            }
            printf("\n\n========== Registo Terminado! ==========");
            break;



        case 2:
            printf("\n\n");
            printf(">>>>>>>>>> MATRIZ DIARIA DE RESUMO DOS SENSORES <<<<<<<<<<");
            /*
            matriz_diaria(periodo, sensor_temp; matrix, 0, maxLimitTemp, minLimitTemp);
            matriz_diaria(periodoVento, sensor_velc_vento, matrix, 1, maxLimitVelcVento, minLimitVelcVento);
            matriz_diaria(periodoVento, sensor_dir_vento, matrix, 2, maxLimitDirVento, minLimitDirVento);
            matriz_diaria(periodo, sensor_pluvio, matrix, 3, maxLimitPluvio, minLimitPluvio);
            matriz_diaria(periodo, sensor_humd_atm, matrix, 4, maxLimitHumdAtm, minLimitHumdAtm);
            matriz_diaria(periodo, sensor_humd_solo, matrix, 5, maxLimitHumdSolo, minLimitHumdSolo);

            show_stats(matrix, 6, 3);
             */
            break;



        case 3:
            printf("\n\n");
            printf(">>>>>>>>>> EXPORTAR PARA FICHEIRO CSV <<<<<<<<<<");
            exportCsv(sensores, n_sensor, matrix);
            break;

        default:
            printf("Opcao indisponivel. Tente novamente.\n");
            scanf("%d", &opc);
            break;
    }
    return 0;
}
