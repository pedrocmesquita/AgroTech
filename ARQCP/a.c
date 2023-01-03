#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINHAS 100
#define MAX_NUM_LINHAS 256

int main(int argc, char *argv[]) {

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

    // Imprime os elementos de nome_sensor
    printf("Nomes dos Sensores:\n");
    for (int j = 0; j < i; j++) {
        printf("[%d]: %s\n", j, nome_sensor[j]);
    }

    // Solicita ao usuário a posição do nome_sensor que deseja
    int position;
    printf("Escolha a posicao do sensor que deseja: ");
    scanf("%d", &position);

    // Verifica se a posição é válida
    if (position < 0 || position >= i) {
        printf("Erro: Posicao Inválida!\nTente novamente.");
        scanf("%d", &position);
    }

    // Imprime o valor da posição correspondente do freq_sensor
    printf("Valor %d: %s\n", position, freq_sensor[position]);

    return 0;
}






