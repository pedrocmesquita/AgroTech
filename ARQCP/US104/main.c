#include <stdio.h>
#include "asm.h"

int min = 0;
int max = 0;

int main(){
    printf("Que sensor deseja estabelecer limite:\n1- Temperatura\n2- Velocidade do Vento\n3- Direção do Vento\n4- Humidade Atmosférica\n5- Humidade Solo\n6- Pluviosidade\n");
    int opc = scanf("Escolha uma opção.\n");
    int res, aux = 3;
    switch (opc){
        case 1:
            min = 1;
            max = 2;
            int valor  = // valor medio lido em 3
            res = limite_sensor(max,min, valor);
            while(res == 0){
                for(int i = 1; i < n; i++){
                    printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.", n-i );
                    if(n == 0){
                        printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");
                        //chamar funcao que gera valores aleatorios
                        break;
                    }
                }
            }

            break;
        
        case 2:
            min = 1;
            max = 2;
            int valor  = // valor medio lido em 3
            res = limite_sensor(max,min, valor);

            break;

        case 3:
            min = 1;
            max = 2;
            int valor  = // valor medio lido em 3
            res = limite_sensor(max,min, valor);
        
            break;

        case 4:
            min = 1;
            max = 2;
            int valor  = // valor medio lido em 3
            res = limite_sensor(max,min, valor);
            
            break;

        case 5:
            min = 1;
            max = 2;
            int valor  = // valor medio lido em 3
            res = limite_sensor(max,min, valor);
            
            break;

        case 6:
            min = 1;
            max = 2;
            int valor  = // valor medio lido em 3
            res = limite_sensor(max,min, valor);
            
            break;
        
        default:
            opc = scanf("Opção Inválida. Tente novamente.");
            break;
    }
    
}