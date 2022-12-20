#include "arqcp.h"
#include <stdio.h>


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

    sensores[i].max_limit = max;
    sensores[i].min_limit = min;

    printf("Os valores são possiveis");
    return 1;
}

char escolher_frequencia(Sensor *sensores, int i){
    unsigned long freq;

    int opc_freq = scanf("Deseja: \n1- Inserir frequencia \n2-Inserir através de um ficheiro\n");
    while (opc_freq < 1 || opc_freq > 2){
        printf("Opção Inválida. Tente novamente.\n");
        scanf("%d", &opc_freq);
    }


    if (opc_freq == 1)
    {
        freq = scanf("Escolha a freqência do sensor:");
        sensores[i].frequency = freq;
        sensores[i].readings_size = sensores[i].frequency;
    }else{
        FILE file;

        // inserir metodo para ler a partir de ficheiro

    }
    return 1;
}
