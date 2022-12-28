#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_LINHAS 100
#define MAX_NUM_LINHAS 256

typedef struct
{
    unsigned short id;
    unsigned char sensor_type;
    unsigned short max_limit; // limites do sensor
    unsigned short min_limit;
    unsigned long frequency; // frequency de leituras (em segundos)
    unsigned long readings_size; // tamanho do array de leituras
    unsigned short *readings; // array de leituras diárias
}Sensor;



char escolher_max_min(float max, float min, float valor, char tentativas, Sensor *sensores){
    return 1;
}
char escolher_frequencia(unsigned long freq2);




int main(){
    int opc;
    char tentativas = 3;
    float matrix[6][3];

    printf("Bem vindo\nEscolha uma das seguintes:\n1) Inserir sensor \n2) Matriz diaria de resumo dos sensores \n");
    scanf("%d", &opc);


    switch (opc) {
        case 1:
            printf("\n\n");
            printf(">>>>>>>>>> INSERIR SENSOR <<<<<<<<<<\n");
            printf("Quantos sensores deseja inserir?\n");
            int n_sensor, i = 1;
            float valor, max, min, freq = 0;
            scanf("%d", &n_sensor);

            Sensor *sensores = malloc(n_sensor * sizeof(Sensor));

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
                        sensores[i].max_limit = max;
                        sensores[i].min_limit = min;

                        // Frequencia
                        sensores[i].frequency = freq;
                        sensores[i].readings_size = sensores[i].frequency;



                        printf("Registo concluido com sucesso!\n\n");
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





/*
char escolher_max_min(float max, float min, float valor, char tentativas, Sensor *sensores){
    int res = limite_sensor(max,min, valor, tentativas);

    while(res == 0){
        for(int j = 1; i < tentativas; j++){
            printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.\n", tentativas-j );
            printf("Minimo:");
            scanf("%f", &min);
            printf("Máximo");
            scanf("%f", &max);

            res = limite_sensor(max,min, valor,tentativas-j);
            if(tentativas - j == 0){
                printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");

                if (!initialize()){
                    for (int i = 0; i < 10; i++){
                        printf("%u\n",pcg32_random_r());
                    }
                }else {
                    printf("An error occured initializing the random number generator.\n");
                    return 0;
                }
                break;
            }
        }
    }

    printf("Os valores são possiveis");
    return 1;
}
*/

char escolher_frequencia(unsigned long freq2){
    unsigned long freq;
    int opc_freq;

    printf("Deseja: \n1- Inserir frequencia \n2- Inserir atraves de um ficheiro\n");
    scanf("%d", &opc_freq);

    while (opc_freq < 1 || opc_freq > 2){
        printf("Opção Inválida. Tente novamente.\n");
        scanf("%d", &opc_freq);
    }


    if (opc_freq == 1)
    {
        printf("Escolha a frequencia do sensor:\n");
        scanf("%ld", &freq);
        freq2 = freq;

    }else{

        char filename[100]; // string para armazenar o nome do arquivo
        char lines[MAX_NUM_LINHAS][MAX_LINHAS];
        char *nome_sensor[MAX_NUM_LINHAS]; // array para nome sensores
        char *freq_sensor[MAX_NUM_LINHAS]; // array para frequencias

        printf("Digite o nome do arquivo: ");
        scanf("%s", filename);

        FILE *file = fopen(filename, "r");
        if (file == NULL) {
            printf("ERRO: Impossivel ler o ficheiro!");
            return 0;
        }


        // Lê cada linha do arquivo e divide-a em dois elementos separados por ","
        int i = 0;
        while (fgets(lines[i], MAX_LINHAS, file) != NULL) {
            // Ignora a primeira linha
            if (i == 0) {
                i++;
                continue;
            }

            // 1 elemento
            char *token = strtok(lines[i], ",");
            nome_sensor[i] = token;
            // 2 elemento
            token = strtok(NULL, ",");
            freq_sensor[i] = token;

            i++;
        }

        fclose(file);

        printf("\n-> Nomes dos Sensores:\n");
        for (int j = 0; j < i; j++) {
            printf("[%d]: %s\n", j, nome_sensor[j]);
        }

        int position;
        printf("Escolha a posicao do sensor que deseja:\n ");
        scanf("%d", &position);

        if (position < 0 || position >= i) {
            printf("Erro: Posicao Inválida!\nTente novamente.");
            scanf("%d", &position);
        }

        printf("\nValor %d: %s\n", position, freq_sensor[position]);
        freq2 = *freq_sensor[position];

    }
    return 1;
}