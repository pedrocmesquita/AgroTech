#include "arqcp.h"
#include <stdio.h>
#include <string.h>
#define MAX_LINHAS 100
#define MAX_NUM_LINHAS 256


char escolher_max_min(float max, float min, float valor, char tentativas, Sensor *sensores, int i){
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

char escolher_frequencia(unsigned long freq2){
    unsigned long freq;

    int opc_freq = scanf("Deseja: \n1- Inserir frequencia \n2-Inserir através de um ficheiro\n");
    while (opc_freq < 1 || opc_freq > 2){
        printf("Opção Inválida. Tente novamente.\n");
        scanf("%d", &opc_freq);
    }


    if (opc_freq == 1)
    {
        printf("Escolha a frequência do sensor:\n");
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

        printf("Nomes dos Sensores:\n");
        for (int j = 0; j < i; j++) {
            printf("[%d]: %s\n", j, nome_sensor[j]);
        }

        int position;
        printf("Escolha a posicao do sensor que deseja: ");
        scanf("%d", &position);

        if (position < 0 || position >= i) {
            printf("Erro: Posicao Inválida!\nTente novamente.");
            scanf("%d", &position);
        }

        printf("Valor %d: %s\n", position, freq_sensor[position]);
        freq2 = *freq_sensor[position];

    }
    return 1;
}
