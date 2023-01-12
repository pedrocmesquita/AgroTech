#ifndef ASM_H
#define ASM_H
void matriz_diaria (int periodo, int * sensor, float matriz[6][3], int linha, int upperLimit, int lowerLimit);
int isValid(int number, int lower, int upper);
int show_stats(float matrix[6][3], int rows, int cols);
#endif
