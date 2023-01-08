#include <stdio.h>
#include "asm.h"

int min = 0;
int max = 0;

int main() {
    int res, tentativas = 3, i = 1;
    float valor = 5, max, min;
    printf("Maximo:\n");
    scanf("%f", &max);

    printf("Minimo:\n");
    scanf("%f", &min);

    res = limite_sensor(max, min, valor);
    while (res == 0) {
        printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.", tentativas - i);
        printf("Maximo:\n");
        scanf("%f", &max);

        printf("Minimo:\n");
        scanf("%f", &min);
        res = limite_sensor(max, min, valor);

        if (tentativas - i== 0) {
            printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");
            //chamar funcao que gera valores aleatorios
            break;
        }

    }
}
