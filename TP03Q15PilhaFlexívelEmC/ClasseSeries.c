#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAXTAM    4000


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

typedef struct Celula {
	Jogador jogador;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Series array[MAXTAM];
int n;

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

bool isFim(char *s){
    return (strlen(s) >= 3 && s[0] == 'F' &&
        s[1] == 'I' && s[2] == 'M');
}

Celula* novaCelula(Series j){
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->jogador = j;
    nova->prox = NULL;
    return nova;
}

void start(){
    n=0;
}

void inserir (Series x){
    if(n>=MAXTAM){
        printf("ERRO!");
        exit(1);
    }

    array[n] = x;
    n++;
}

Series remover(){
    if (n == 0) {
      printf("Erro ao remover!");
      exit(1);
   }

   printf("(R) %s\n", array[n-1].nome);

   return array[--n];
}

int tamanho() {
   int i;
   for(i = 0; i < n; i++);
   return i;
}

void mostrar (){
   int i;

   for(i = 0; i < n; i++){
      //printf("[%d]", i);
      imprimir(array[i]);
   }

int main(){
    char nomeArquivo[1000];
    Series jarr[10000];
    int i=0; 
    
    do{
        
        scanf("%[^\n]%*c", nomeArquivo);

        if(strcmp(nomeArquivo, "FIM") != 0){
        jarr[i] = lerSerie(nomeArquivo);
        MostrarSerie(jarr[i]);
        i++;  
        }
          
    }while(strcmp(nomeArquivo, "FIM"));

    
  
    return 0;
}