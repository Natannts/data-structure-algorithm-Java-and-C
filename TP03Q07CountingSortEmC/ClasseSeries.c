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

int getMaior(Series *series, int n) {
    int maior = series[0].qntTemporadas;

    for (int i = 0; i < n; i++) {
        if(maior < series[i].qntTemporadas){
            maior = series[i].qntTemporadas;
        }
    }
    return maior;
}

void countingsort(Series *series, int n) {
    //Series para contar o numero de ocorrencias de cada elemento
    int tamCount = getMaior(series, n) + 1;
    int count[tamCount];
    Series ordenado[n];

    //Inicializar cada posicao de series de contagem 
    for (int i = 0; i < tamCount; count[i] = 0, i++);

    //Agora, o count[i] contem o numero de elemento iguais a i
    for (int i = 0; i < n; count[series[i].qntTemporadas]++, i++);

    //Agora, o count[i] contem o numero de elemento menores ou iguais a i
    for(int i = 1; i < tamCount; count[i] += count[i-1], i++);

    //Ordenando
    for(int i = n-1; i >= 0; ordenado[count[series[i].qntTemporadas]-1] = series[i], count[series[i].qntTemporadas]--, i--){
    numComparacoes++;
    }

    //Copiando para a series original
    for(int i = 0; i < n; series[i] = ordenado[i], i++);
}

void insercao(Series *series, int n){
    for (int i = 1; i < n; i++) {
      Series tmp = series[i];
      int j = i - 1;

      while ((j >= 0) && (series[j].qntTemporadas == tmp.qntTemporadas) && (strcmp(series[j].nome, tmp.nome)>0)) {
         series[j + 1] = series[j];
         j--;
      }
      series[j+1] = tmp;
   }
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
    countingsort(jarr, i );
    insercao(jarr, i );
    fim = clock();// Finalizar contador de tempo
    tempoExec = ((fim - inicio) / (double)CLOCKS_PER_SEC);
 

    // Arquivo Log
    FILE *arqLog = fopen(" matrícula_countingsort.txt", "w");
    fprintf(arqLog, "625188\t%d\t%d\t%f\n", numComparacoes, numMovimentacoes, tempoExec );
    fclose(arqLog);

    // Imprimir Series
    for (int j = 0; j < i; j++)
    {
        MostrarSerie(jarr[j]);
    }
    return 0;
}