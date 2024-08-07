#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <assert.h>
#include <time.h>
#include <math.h>
#include <ctype.h>
#define MaxLista 640
#define _GNU_SOURCE

typedef struct
{
    char nome[50];
    char idioma[50];
    char formato[50];
    char duracao[50];
    char paisOrigem[50];
    char emissoraOriginal[50];
    char transmicaoOriginal[100];
    int qntTemporadas;
    int qntEpisodios;
} Series;

char *removeTags(char *old)
{
    char *newLine = (char *)malloc(25 * sizeof(strlen(old)));
    int i = 0, j = 0;

    while (i < strlen(old))
    {
        if (old[i] == '<')
        {
            i++;
            while (old[i] != '>')
                i++;
        }
        else if (old[i] == '&')
        {
            i++;
            while (old[i] != ';')
                i++;
        }
        else if (old[i] == '\n')
        {
            i++;
        }
        else
        {
            newLine[j] = old[i];
            j++;
        }
        i++;
    }

    newLine[j] = '\0';

    return newLine;
}

char *retornaNome(char *a)
{
    int i = 0;
    char branco = ' ';
    char *arquivo = strtok(a, ".");
    while (i < strlen(a))
    {
        if (a[i] == '_')
        {
            a[i] = ' ';
        }
        i++;
    }
    return a;
}

/**
 * Remove leading whitespace characters from string
 */
char *trim(char *str)
{
    size_t len = 0;
    char *frontp = str;
    char *endp = NULL;

    if (str == NULL)
    {
        return NULL;
    }
    if (str[0] == '\0')
    {
        return str;
    }

    len = strlen(str);
    endp = str + len;

    /* Move the front and back pointers to address the first non-whitespace
     * characters from each end.
     */
    while (isspace((unsigned char)*frontp))
    {
        ++frontp;
    }
    if (endp != frontp)
    {
        while (isspace((unsigned char)*(--endp)) && endp != frontp)
        {
        }
    }

    if (frontp != str && endp == frontp)
        *str = '\0';
    else if (str + len - 1 != endp)
        *(endp + 1) = '\0';

    /* Shift the string so that it starts at str so that if it's dynamically
     * allocated, we can still free it on the returned pointer.  Note the reuse
     * of endp to mean the front of the string buffer now.
     */
    endp = str;
    if (frontp != str)
    {
        while (*frontp)
        {
            *endp++ = *frontp++;
        }
        *endp = '\0';
    }

    return str;
}

Series lerSerie(char *nomeArquivo)
{
    char abrir[50] = {"/tmp/series/"};
    strcat(abrir, nomeArquivo);
    FILE *arq = fopen(abrir, "r");
    Series serie;

    if (arq == NULL)
    {
        printf("Erro ao tentar abrir o arquivo!\n");
        // exit(0);
    }

    char buf[10000];
    char *tmp;

    fgets(buf, 10000, arq);

    // Nome
    while (strstr(buf, "infobox_v2") == NULL)
    {
        fgets(buf, 10000, arq);
    }
    fgets(buf, 10000, arq);
    fgets(buf, 10000, arq);
    strcpy(serie.nome, retornaNome(nomeArquivo));

    // Formato
    while (strstr(buf, "Formato") == NULL)
    {
        fgets(buf, 10000, arq);
    }
    fgets(buf, 10000, arq);
    strcpy(serie.formato, removeTags(buf));

    // Duraçao
    while (strstr(buf, "Duração") == NULL)
    {
        fgets(buf, 10000, arq);
    }
    fgets(buf, 10000, arq);
    strcpy(serie.duracao, removeTags(buf));

    // País de origem
    while (strstr(buf, "País de origem") == NULL)
    {
        fgets(buf, 10000, arq);
    }
    fgets(buf, 10000, arq);
    tmp = removeTags(buf);
    strcpy(serie.paisOrigem, trim(tmp));

    // Idioma original
    while (strstr(buf, "Idioma original") == NULL)
    {
        fgets(buf, 10000, arq);
    }
    fgets(buf, 10000, arq);
    tmp = removeTags(buf);
    strcpy(serie.idioma, trim(tmp));

    // Emissora de televisão original
    while (strstr(buf, "Emissora de televisão original") == NULL)
    {
        fgets(buf, 10000, arq);
    }
    fgets(buf, 10000, arq);
    tmp = removeTags(buf);
    strcpy(serie.emissoraOriginal, trim(tmp));

    // Transmissão original
    while (strstr(buf, "Transmissão original") == NULL)
    {
        fgets(buf, 10000, arq);
    }

    fgets(buf, 10000, arq);
    tmp = removeTags(buf);
    strcpy(serie.transmicaoOriginal, trim(tmp));

    // N.º de temporadas
    while (strstr(buf, "N.º de temporadas") == NULL)
    {
        fgets(buf, 10000, arq);
    }
    fgets(buf, 10000, arq);
    serie.qntTemporadas = atoi(removeTags(buf));

    // N.º de episódios
    while (strstr(buf, "N.º de episódios") == NULL)
    {
        fgets(buf, 10000, arq);
    }
    fgets(buf, 10000, arq);
    serie.qntEpisodios = atoi(removeTags(buf));

    fclose(arq);
    return serie;
}

void MostrarSerie(Series serie)
{
    printf("%s %s %s %s %s %s %s %d %d\n", serie.nome, serie.formato, serie.duracao, serie.paisOrigem, serie.idioma,
           serie.emissoraOriginal, serie.transmicaoOriginal, serie.qntTemporadas, serie.qntEpisodios);
}

// Lista:
int contador = 0, numComparacoes = 0, numMovimentacoes = 0;
Series series[MaxLista];

// Metodos:

void swap(Series *serie, int i, int j)
{
    Series a = serie[i];
    serie[i] = serie[j];
    serie[j] = a;
    numMovimentacoes += 3;
}

void insercaoPorCor(Series *series, int n, int cor, int h){
    for (int i = (h + cor); i < n; i+=h) {
        Series tmp = series[i];
        int j = i - h;
        while ((j >= 0) && (strcmp(series[j].idioma, tmp.idioma) > 0)
                ||(j >= 0) && (strcmp(series[j].idioma, tmp.idioma) == 0) && (strcmp(series[j].nome, tmp.nome) > 0 )){
            
            series[j + h] = series[j];
            j-=h;
        }
        series[j + h] = tmp;
    }
}

void shellsort(Series *series, int n) {
    int h = 1;

    do { h = (h * 3) + 1; } while (h < n);

    do {
        h /= 3;
        for(int cor = 0; cor < h; cor++){
            insercaoPorCor(series, n, cor, h);
        }
    } while (h != 1);
}

int main()
{
    char nomeArquivo[1000];
    Series jarr[10000];
    int i = 0;
    clock_t inicio, fim;
    double tempoExec;

    do
    {

        scanf("%[^\n]%*c", nomeArquivo);

        if (strcmp(nomeArquivo, "FIM") != 0)
        {
            jarr[i] = lerSerie(nomeArquivo);
            // MostrarSerie(jarr[i]);
            i++;
        }

    } while (strcmp(nomeArquivo, "FIM"));

    inicio = clock(); // Inicializar contador de tempo
    shellsort(jarr, i );
    fim = clock();// Finalizar contador de tempo
    tempoExec = ((fim - inicio) / (double)CLOCKS_PER_SEC);
 

    // Arquivo Log
    FILE *arqLog = fopen(" matrícula_shellsort.txt", "w");
    fprintf(arqLog, "625188\t%d\t%d\t%f\n", numComparacoes, numMovimentacoes, tempoExec );
    fclose(arqLog);

    // Imprimir Series
    for (int j = 0; j < i; j++)
    {
        MostrarSerie(jarr[j]);
    }
    return 0;
}