#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>
#define TamEntrada 80
#define MaxLista 640
#define _GNU_SOURCE

Series *bolha(Series *seri, int n, int k)
{
    int i, j;
    if (i < n)
    {
        for (j = 0; j < i; j++)
        {
            if (strcmp(seri[j].paisOrigem, seri[j + 1].paisOrigem) > 0)

            {
                swap(seri, j, j + 1);
            }
            else if (strcmp(seri[j].paisOrigem, seri[j + 1].paisOrigem) > 0)
            {
                if ((strcmp(seri[j].nome, seri[j + 1].nome) > 0))
                {
                    swap(seri, j, j + 1);
                }
            }
        }
    }

    return bolha(series, n, i + 1);

    for (int j = 0; j < n; j++)
    {
        MostrarSerie(seri[j]);
    }
    return seri;
}


typedef struct {
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

char* removeTags(char* old){
    char *newLine = (char*) malloc(25 * sizeof(strlen(old)));
    int i=0, j=0;
    
    while(i<strlen(old)){
        if(old[i] == '<'){
            i++;
            while(old[i] != '>')
                i++;
        }else if(old[i] == '&'){
            i++;
            while(old[i] != ';')
                i++;
        }else if(old[i] == '\n'){
            i++;
        }else{
            newLine[j] = old[i];
            j++;
        }
        i++;
    }

    newLine[j] = '\0';
    
    return newLine;
}

char* retornaNome(char* a){
    int i=0;
    char branco = ' ';
    char* arquivo = strtok(a, ".");
    while(i<strlen(a)){
    if(a[i] == '_'){
        a[i] = ' ';
    }
    i++;
    }
    return a;
}

Series lerSerie(char* nomeArquivo){
    char abrir[50] = {"/tmp/series/"};
    strcat(abrir, nomeArquivo);
    FILE *arq = fopen(abrir, "r");
    Series serie;

    if (arq == NULL) {
        printf("Erro ao tentar abrir o arquivo!\n");
        //exit(0);
    }

    char buf[10000];

    fgets(buf,10000,arq);

//Nome
    while(strstr(buf,"infobox_v2") == NULL){
        fgets(buf,10000, arq);
    }
    fgets(buf,10000, arq);
    fgets(buf,10000, arq);
    strcpy(serie.nome, retornaNome(nomeArquivo));

//Formato
    while(strstr(buf,"Formato") == NULL){
        fgets(buf,10000, arq);
    }
    fgets(buf,10000,arq);
    strcpy(serie.formato, removeTags(buf));
    

//Duraçao
    while(strstr(buf,"Duração") == NULL){
        fgets(buf,10000, arq);
    }
    fgets(buf,10000,arq);
    strcpy(serie.duracao, removeTags(buf));

//País de origem
    while(strstr(buf,"País de origem") == NULL){
        fgets(buf,10000, arq);
    }
    fgets(buf,10000,arq);
    strcpy(serie.paisOrigem, removeTags(buf));

//Idioma original
    while(strstr(buf,"Idioma original") == NULL){
        fgets(buf,10000, arq);
    }
    fgets(buf,10000,arq);
    strcpy(serie.idioma, removeTags(buf));

//Emissora de televisão original
    while(strstr(buf,"Emissora de televisão original") == NULL){
        fgets(buf,10000, arq);
    }
    fgets(buf,10000,arq);
    strcpy(serie.emissoraOriginal, removeTags(buf));

//Transmissão original
    while(strstr(buf,"Transmissão original") == NULL){
        fgets(buf,10000, arq);
    }

    fgets(buf,10000,arq);
    strcpy(serie.transmicaoOriginal, removeTags(buf));

//N.º de temporadas
    while(strstr(buf,"N.º de temporadas") == NULL){
        fgets(buf,10000, arq);
    }
    fgets(buf,10000,arq);
    serie.qntTemporadas = atoi(removeTags(buf));

//N.º de episódios
    while(strstr(buf,"N.º de episódios") == NULL){
        fgets(buf,10000, arq);
    }
    fgets(buf,10000,arq);
    serie.qntEpisodios = atoi(removeTags(buf));
    

    fclose(arq);
    return serie;
}

void MostrarSerie(Series serie){
    printf("%s %s %s %s %s %s %s %d %d\n"
            ,serie.nome, serie.formato, serie.duracao, serie.paisOrigem, serie.idioma, 
            serie.emissoraOriginal, serie.transmicaoOriginal, serie.qntTemporadas, serie.qntEpisodios);
}

//Lista:
int contador = 0, numComparacoes = 0, numMovimentacoes = 0;
double tempoExec;
Series series[MaxLista];

//Metodos:
void inserirFim(Series *t){
    //Validar insercao:
    if(contador >= MaxLista){
        printf("\nErro! Lista cheia!\n");
        exit(1);
    }

    series[contador++] = *t; //Registrar e realizar insercao
}

void mostrar(){
    for(int i=0; i<contador; i++){
        MostrarSerie(series[i]);
    }
}

void swap(int menor, int i){
    Series tmp = series[i];
    series[i] = series[menor];
    series[menor]=tmp;
        
    //Registrar operacao relevante:
    numMovimentacoes += 3;
}

void selecaoRec(Series *series, int n, int i){
    clock_t inicioExec = clock(); //Inicializar contador de tempo

    if(i >= n-1){
        return;
    }

    int menor = i;

    for(int j = (i+1); j<n; j++){
        if(strcmp(series[menor].paisOrigem, series[j].nome) > 0){
            menor = j;
        }
    }

    swap(menor,i);

    selecaoRec(series, n, i+1);

    int comparaSelect = 0;
    comparaSelect = (int)(pow((n-1), 2) + (n-1))/2;
    numComparacoes += comparaSelect;

    tempoExec = (clock()-inicioExec) / (double)CLOCKS_PER_SEC; //Retornar o tempo de execucao (em segundos) do selection recursivo
    
   /* if(j < contador){
        bool ehMaior = (strcmp(series[j].paisOrigem, series[menor].paisOrigem) > 0 );    numComparacoes++; //Registrar operacao relevante
        if(!ehMaior){
            numComparacoes; //Registrar operacao relevante (feita na condicao do if abaixo)
            if(series[j].paisOrigem == series[menor].paisOrigem){
                if( strcmp(series[j].nome, series[menor].nome) < 0 ) menor = j;
                numComparacoes ++; //Registrar operacao relevante extra feita neste bloco
            }

            else menor = j; //A unica opcao restante eh se "series[j].paisOrigem < series[menor].paisOrigem"
            //Obs: Os testes foram divididos desta forma para permitir o calculo exato do numero de comparacoes realizadas
        }

        menor = selecaoRec(menor, j+1); //Proxima posicao
    }

    else return menor;*/
}
void selecaoRec1(int i){ //Equivalente ao laco externo (variavel i)
    if(i < (contador-1)){
        int menor = selecaoRec(i, i+1); //Inicializar contador de recursividade (variavel de controle 'j')

        //Se necessario, realizar swap (economizando movimentacoes entre elementos array):
        if(i!=menor) swap(menor, i);

        selecaoRec1(i+1); //Proxima posicao
    }
}
void selecaoRec2(){
    clock_t inicioExec = clock(); //Inicializar contador de tempo

    selecaoRec1(0); //Iniciar algoritmo de selecao recursivo

    tempoExec = (clock()-inicioExec) / (double)CLOCKS_PER_SEC; //Retornar o tempo de execucao (em segundos) do selection recursivo
}

int main(){
    char entrada[TamEntrada];

    //Primeira Parte - Entrada Padrao:
    fgets(entrada, TamEntrada, stdin); //Ler endereco do arquivo
    while(strncmp(entrada, "FIM", 3) != 0){
        Series *series = (Series*) malloc( sizeof(Series) ); //Instanciar novo series
        lerSerie(strdup(entrada)); //Registrar atributos do series
        inserirFim(series); //Registrar series na lista

        //Liberar memorias:
        free(series); series = NULL;

        fgets(entrada, TamEntrada, stdin); //Realizar proxima leitura
    }

    selecaoRec(series, ); //Organizar lista

    //Arquivo log:
    FILE *arqLog = fopen(" matrícula_selecaoRecursiva.txt", "w");
    fprintf(arqLog, "625188\t%d\t%d\t%f\n", numComparacoes, numMovimentacoes, tempoExec);
    fclose(arqLog);

    //Imprimir a lista:
    mostrar();
    return 0;
}