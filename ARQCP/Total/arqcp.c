#include <stdio.h>
#include <unistd.h>
#include "arqcp.h"
#define tentativas 3;

extern long state;
extern long inc;

int main(void) {
    printf("\n---> US101:\n");

    //us101
    if (!initialize())    //initialization successful
    {
        for (int i = 0; i < 10; i++) {
            printf("%u\n", pcg32_random_r());
        }
    } else {
        printf("An error occured initializing the random number generator.\n");
    }


// ===========================================================================================================================
	printf("\n---> US102:\n");

	if (!initialize())	//initialization successful
	{
		int sec = 2;

		char tmp = pcg32_random_r() % 61 - 20;					//[0, 60] - 20 = [-20, 40] (ºC)
		unsigned char wind_vel = pcg32_random_r() % 51;			//[0, 50] (km/h)
		unsigned short wind_dir = pcg32_random_r() % 360;		//[0, 359] (º)
		unsigned char humd_atm = pcg32_random_r() % 101;		//[0, 100] (%)
		unsigned char humd_grnd = pcg32_random_r() % 101;		//[0, 100] (%)
		unsigned char rain = pcg32_random_r() % 26;				//[0,25] (mm)

		while(1)
		{
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

			sleep(sec);
		}

	}

	else
	{
		printf("An error occured initializing the random number generator.\n");
	}

// ===========================================================================================================================
	printf("\n---> US103:\n");

	float matrix[6][3];

    matriz_diaria(periodo, sensor_temp, matrix, 0, maxLimitTemp, minLimitTemp);
    matriz_diaria(periodoVento, sensor_velc_vento, matrix, 1, maxLimitVelcVento, minLimitVelcVento);
    matriz_diaria(periodoVento, sensor_dir_vento, matrix, 2, maxLimitDirVento, minLimitDirVento);
    matriz_diaria(periodo, sensor_pluvio, matrix, 3, maxLimitPluvio, minLimitPluvio);
    matriz_diaria(periodo, sensor_humd_atm, matrix, 4, maxLimitHumdAtm, minLimitHumdAtm);
    matriz_diaria(periodo, sensor_humd_solo, matrix, 5, maxLimitHumdSolo, minLimitHumdSolo);

    show_stats(matrix, 6, 3);

// ==========================================================================================================================
	printf("\n---> US104:\n");

	printf("Que sensor deseja estabelecer limite:\n1- Temperatura\n2- Velocidade do Vento\n3- Direção do Vento\n4- Humidade Atmosférica\n5- Humidade Solo\n6- Pluviosidade\n");
    int opc = scanf("Escolha uma opção.\n");
    int res, n = tentativas;
	int min, max;

    switch (opc){
        case 1:
            min = scanf("Minimo:");
            max = scanf("Máximo");
            int valor  = *(*(matrix + 0) + 2);
            res = limite_sensor(max,min, valor, n);

            while(res == 0){
                for(int i = 1; i < n; i++){
                    printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.", n-i );
                    //valor = funcao
                    res = limite_sensor(max,min, valor,n);
                    if(n - i == 0){
                        printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");
                        //chamar funcao que gera valores aleatorios
                        break;
                    }
                }
            }
            printf("Os valores são possiveis máximo e minimo");
            break;

        case 2:
            min = scanf("Minimo:");
            max = scanf("Máximo");
            int valor  = *(*(matrix + 1) + 2);
            res = limite_sensor(max,min, valor,n);

            while(res == 0){
                for(int i = 1; i < n; i++){
                    printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.", n-i );
                    //valor = funcao
                    res = limite_sensor(max,min, valor,n);
                    if(n - i == 0){
                        printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");
                        //chamar funcao que gera valores aleatorios
                        break;
                    }
                }
            }
            printf("Os valores são possiveis máximo e minimo");
            break;

        case 3:
            min = scanf("Minimo:");
            max = scanf("Máximo");
            int valor  = *(*(matrix + 2) + 2);
            res = limite_sensor(max,min, valor,n);

            while(res == 0){
                for(int i = 1; i < n; i++){
                    printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.", n-i );
                    //valor = funcao
                    res = limite_sensor(max,min, valor,n);
                    if(n - i == 0){
                        printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");
                        //chamar funcao que gera valores aleatorios
                        break;
                    }
                }
            }
            printf("Os valores são possiveis máximo e minimo");
            break;

        case 4:
            min = scanf("Minimo:");
            max = scanf("Máximo");
            int valor  = *(*(matrix + 3) + 2);
            res = limite_sensor(max,min, valor,n);

            while(res == 0){
                for(int i = 1; i < n; i++){
                    printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.", n-i );
                    //valor = funcao
                    res = limite_sensor(max,min, valor,n);
                    if(n - i == 0){
                        printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");
                        //chamar funcao que gera valores aleatorios
                        break;
                    }
                }
            }
            printf("Os valores são possiveis máximo e minimo");
            break;

        case 5:
            min = scanf("Minimo:");
            max = scanf("Máximo");
            int valor  = *(*(matrix + 4) + 2);
            res = limite_sensor(max,min, valor,n);


            while(res == 0){
                for(int i = 1; i < n; i++){
                    printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.", n-i );

                    res = limite_sensor(max,min, valor,n);
                    if(n - i == 0){
                        printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");
                        //chamar funcao que gera valores aleatorios
                        break;
                    }
                }
            }
            printf("Os valores são possiveis máximo e minimo");
            break;

        case 6:
            min = scanf("Minimo:");
            max = scanf("Máximo");
            int valor  = *(*(matrix + 0) + 2);
            res = limite_sensor(max,min, valor,n);

            while(res == 0){
                for(int i = 1; i < n; i++){
                    printf("======== ERRO ========\nValor fora de limite!\nTem mais %d opções.", n-i );

                    res = limite_sensor(max,min, valor,n);
                    if(n - i == 0){
                        printf("NÃO TEM MAIS TENTATIVAS!\n\n\n REINICIANDO ... ");
                        //chamar funcao que gera valores aleatorios
                        break;
                    }
                }
            }
            printf("Os valores são possiveis máximo e minimo");
            break;

        default:
            opc = scanf("Opção Inválida. Tente novamente.");
            break;
    }

    return 0;
}

void show_stats(float matrix[6][3], int rows, int cols){

    float min, max, med;

    printf("\n\n<-----------------STATS----------------->\n\n");

    max = *(*(matrix + 0) + 0);
    min = *(*(matrix + 0) + 1);
    med = *(*(matrix + 0) + 2);

    printf("Temperatura:\n");

    printf("Max: %.2f\n", max);
    printf("Min: %.2f\n", min);
    printf("Med: %.2f\n", med);

    max = *(*(matrix + 1) + 0);
    min = *(*(matrix + 1) + 1);
    med = *(*(matrix + 1) + 2);

    printf("\nVelocidade do Vento:\n");

    printf("Max: %.2f\n", max);
    printf("Min: %.2f\n", min);
    printf("Med: %.2f\n", med);

    max = *(*(matrix + 2) + 0);
    min = *(*(matrix + 2) + 1);
    med = *(*(matrix + 2) + 2);

    printf("\nDirecao do Vento:\n");

    printf("Max: %.2f\n", max);
    printf("Min: %.2f\n", min);
    printf("Med: %.2f\n", med);

    max = *(*(matrix + 3) + 0);
    min = *(*(matrix + 3) + 1);
    med = *(*(matrix + 3) + 2);

    printf("\nPluviometro:\n");

    printf("Max: %.2f\n", max);
    printf("Min: %.2f\n", min);
    printf("Med: %.2f\n", med);

    max = *(*(matrix + 4) + 0);
    min = *(*(matrix + 4) + 1);
    med = *(*(matrix + 4) + 2);

    printf("\nHumidade do Ar:\n");

    printf("Max: %.2f\n", max);
    printf("Min: %.2f\n", min);
    printf("Med: %.2f\n", med);

    max = *(*(matrix + 5) + 0);
    min = *(*(matrix + 5) + 1);
    med = *(*(matrix + 5) + 2);

    printf("\nHumidade do Solo:\n");

    printf("Max: %.2f\n", max);
    printf("Min: %.2f\n", min);
    printf("Med: %.2f\n", med);
}