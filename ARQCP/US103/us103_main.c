#include <stdio.h>

void main(void){

    float matrix[6][3];

    matriz_diaria(periodo, sensor_temp; matrix, 0, maxLimitTemp, minLimitTemp);
    matriz_diaria(periodoVento, sensor_velc_vento, matrix, 1, maxLimitVelcVento, minLimitVelcVento);
    matriz_diaria(periodoVento, sensor_dir_vento, matrix, 2, maxLimitDirVento, minLimitDirVento);
    matriz_diaria(periodo, sensor_pluvio, matrix, 3, maxLimitPluvio, minLimitPluvio);
    matriz_diaria(periodo, sensor_humd_atm, matrix, 4, maxLimitHumdAtm, minLimitHumdAtm);
    matriz_diaria(periodo, sensor_humd_solo, matrix, 5, maxLimitHumdSolo, minLimitHumdSolo);

    show_stats(matrix, 6, 3);

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