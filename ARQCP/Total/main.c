#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include "arqcp.h"


int main(){
    int opc;
    char tentativas = 3;
    float matrix[6][3];
    int n_sensor = 1;

    Sensor *sensores = malloc(n_sensor * sizeof(Sensor));

    printf("Bem vindo\nEscolha uma das seguintes:\n1) Inserir sensor \n2) Matriz diaria de resumo dos sensores \n3) Exportar para um ficheiro CSV os dados de cada sensor");
    scanf("%d", &opc);


    switch (opc) {
        case 1:
            printf("\n\n>>>>>>>>>> INSERIR SENSOR <<<<<<<<<<\n");
            printf("Quantos sensores deseja inserir?\n");
            int i = 1;
            float valor, max, min, freq = 0;
            scanf("%d", &n_sensor);

            sensores = realloc(sensores, n_sensor * sizeof (Sensor));


            if (sensores == NULL) {
                fprintf(stderr, "Erro: Alocacao de memoria\n");
                return 0;
            }
            /* =================================== INSERIR DADOS CONFORME O RESTO DAS USs =================================================== */



            while (n_sensor != 0){
                printf("<<< SENSOR %d >>>\n", i );
                int opc_sensor;
                printf("\n-> Selecione o tipo do Sensor:\n1- Temperatura\n2- Velocidade do Vento\n3- Direcao do Vento\n4- Humidade Atmosferica\n5- Humidade Solo\n6- Pluviosidade\n");
                scanf("%d",&opc_sensor);

                while (opc_sensor < 1 || opc_sensor > 6){
                    printf("Opcao indisponivel. Tente novamente.\n");
                    scanf("%d", &opc_sensor);
                }

                // Array Leituras
                // matriz_diaria(); -------------------------------------------------> IMPORTANTE !!!!

                printf("Valor Limite Maximo:\n");
                scanf("%f", &max);

                printf("Valor Limite Minimo\n");
                scanf("%f", &min);

                while (max - min < 0){
                    printf("Valores maximo e minimo nao sao compativeis. Tente novamente\n");
                    printf("Valor Limite Maximo:\n");
                    scanf("%f", &max);

                    printf("Valor Limite Minimo\n");
                    scanf("%f", &min);
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
                        sensores[i].max_limit = (unsigned short) max;
                        sensores[i].min_limit = (unsigned short) min;

                        // Frequencia
                        sensores[i].frequency = (unsigned long) freq;
                        sensores[i].readings_size = sensores[i].frequency;



                        printf("Registo concluido com sucesso!\n\nId sensor é %d", sensores[i].id);
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


        case 3:
            printf("\n\n>>>>>>>>>> EXPORTE FICHEIRO CSV <<<<<<<<<<");
            exportCsv(sensores, n_sensor,matrix);
            break;

        default:
            printf("Opcao indisponivel. Tente novamente.\n");
            scanf("%d", &opc);
            break;
    }
    return 0;
}

