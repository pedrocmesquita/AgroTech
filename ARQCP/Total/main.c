#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include "arqcp.h"




int main(){
    int opc;
    char tentativas = 3;
    float matrix[6][3];

    printf("Bem vindo\nEscolha uma das seguintes:\n1) Inserir um sensor \n2) Matriz diaria de resumo dos sensores \n");
    scanf("%d", &opc);


    switch (opc) {
        case 1:
            printf("\n\n");
            printf(">>>>>>>>>> INSERIR SENSOR <<<<<<<<<<");
            printf("Quantos sensores deseja inserir?");
            int n_sensor, i = 0;
            float valor, max, min;
            scanf("%d", &n_sensor);

            Sensor *sensores = malloc(n_sensor * sizeof(Sensor));

            if (sensores == NULL) {
                fprintf(stderr, "Error allocating memory\n");
                return 0;
            }
            /* =================================== INSERIR DADOS CONFORME O RESTO DAS USs =================================================== */

            int opc_sensor;
            printf("> Selecione o tipo do Sensor:\n1- Temperatura\n2- Velocidade do Vento\n3- Direção do Vento\n4- Humidade Atmosférica\n5- Humidade Solo\n6- Pluviosidade\n");
            scanf("%d",&opc_sensor);

            while (opc_sensor < 1 || opc_sensor > 6){
                printf("Opcao indisponivel. Tente novamente.\n");
                scanf("%d", &opc_sensor);
            }

            while (n_sensor != 0){
                printf("--> SENSOR %d", i++ );
                // Tipo sensor
                sensores[i].sensor_type = opc_sensor;

                // ID SENSOR -- 4 digitos
                sensores[i].id = 1000 + rand() % 9999;

                // Array Leituras
                // matriz_diaria(); -------------------------------------------------> IMPORTANTE !!!!

                printf("Valor Limite Maximo:\n");
                scanf("%d", &max);

                printf("Valor Limite Minimo\n");
                scanf("%d", &min);

                valor = *(*(matrix + opc_sensor - 1) + 2);

                // maximo e minimo
                if(escolher_max_min(max, min, valor,tentativas, sensores, i) == 1) {
                    //frequencia
                    if (escolher_frequencia(sensores, i) == 1) {

                        printf("Registo concluído com sucesso!\n\n");
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
            }
            printf("Registo Terminado!");
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

        default:
            printf("Opcao indisponivel. Tente novamente.\n");
            scanf("%d", &opc);
            break;
    }
    return 0;
}
