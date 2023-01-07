#include <stdio.h>
#include "arqcp.h"

/*
	Em C, exporte para um ficheiro CSV, os dados e leituras de cada um dos sensores. Crie
	outro ficheiro CSV com os dados da matriz diária de resumo
*/

/*
	typedef struct
	{
		unsigned short id;
		unsigned char sensor_type;
		unsigned short max_limit; // limites do sensor
		unsigned short min_limit;
		unsigned long frequency; // frequency de leituras (em segundos)
		unsigned long readings_size; // tamanho do array de leituras
		unsigned short *readings; // array de leituras diárias
	} Sensor;
*/

void exportCsv(Sensor* array, int size, float matrix[6][3])
{
FILE *f;
f = fopen ("sensores.csv", "w");

for (int i = 0; i < size; i++)
{
fprintf(f, "%hu,", array[i].id);
fprintf(f, "%c,", array[i].sensor_type);
fprintf(f, "%hu,", array[i].max_limit);
fprintf(f, "%hu,", array[i].min_limit);
fprintf(f, "%lu,", array[i].frequency);
fprintf(f, "%lu", array[i].readings_size);

for (int j = 0; j < array[i].readings_size; j++)
{
fprintf(f, ",%hu", array[i].readings[j]);
}

fprintf(f, "\n");
}

fclose(f);

f = fopen("matriz_diaria.csv", "w");

for (int i = 0; i < size; i++)
{
fprintf(f, "%f", matrix[i][0]);

for (int j = 1; j < array[i].readings_size; j++)
{
fprintf(f, ",%f", matrix[i][j]);
}

fprintf(f, "\n");
}

fclose(f);
}