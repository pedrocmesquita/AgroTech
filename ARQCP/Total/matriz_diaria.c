#include "arqcp.h"

void matriz_diaria(int periodo, int * sensor, float matriz[6][3], int linha, int limitesuperior, int limiteinferior){
    int minValue, maxValue, genValue, sum;
    float medValue, count;

    maxValue = limiteinferior;
    minValue = limitesuperior;
    sum = 0;
    count = 0;

    for (int i = 0; i < periodo; i++){
        genValue = *(sensor + i);

        if(isValid(genValue, limitesuperior, limiteinferior)){
            if(genValue > maxValue){
                maxValue = genValue;
            }
            if(genValue < minValue){
                minValue = genValue;
            }
            sum += genValue;
            count++;
        }
    }

    if (count != 0){
        medValue = (float)sum/count;
    } else {
        medValue = 0;
        minValue = 0;
        maxValue = 0;
    }

    //max
    *(*(matriz + linha) + 0) = maxValue;
    //min
    *(*(matriz + linha) + 1) = minValue;
    //media
    *(*(matriz + linha) + 2) = maxValue;
}