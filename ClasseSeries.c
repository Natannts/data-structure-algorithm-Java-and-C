#include <stdio.h>
#include <string.h>
#include <stdlib.h>

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

//STRUCT CELULA DUPLA =======================================================================================
typedef struct Celula{
    Series elemento;
    struct Celula *prox;
}Celula;

Celula* novaCelula(){
    Celula *nova = (Celula*) malloc ( sizeof(Celula) );
    nova->prox = NULL;
    return nova;
}
Celula* novaCelula(Series *t){
    Celula *nova = (Celula*) malloc ( sizeof(Celula) );
    nova->elemento = *t;
    nova->prox = NULL;
    return nova;
}

//PILHA FLEXIVEL ============================================================================================
Celula *topo; //Celula cabeca

void inserir(Series *t){
    Celula *tmp = novaCelula(t); //Criar celula temporaria c/ o elemento a ser inserido
    tmp->prox = topo->prox; //Conectar (inserir) celula temporaria na pilha
    topo->prox = tmp; //Atualizar celula "topo"
}

Series remover(){
    if(topo->prox == NULL) errx(1, "Erro ao remover! Pilha vazia!"); //Validar remocao
    
    topo = topo->prox; //Realizar remocao (logica)
    Series removido = topo->elemento;

    return removido;
}

void mostrar(){ //Imprime todos os elementos da pilha a partir do TOPO da mesma
    int cont = 0;
    for(Celula *i = topo->prox; i != NULL; i = i->prox, cont++){
        
        imprimir(&i->elemento);
    }
}

int mostrarRec(Celula *i){ //Imprime todos os elementos da pilha a partir do INICIO da mesma
    int cont;

    if(i != NULL){
        cont = mostrarRec(i->prox) + 1; //Executar recursividade e incrementar contador 
        //Imprimir Series:
        imprimir(&i->elemento);
    }
    else cont = -1; //Quando for o fim, retornar -1

    return cont;
}
void mostrarRec(){ //Inicializa recursividade
    Celula *i = topo->prox;
    mostrarRec(i);

    free(i); i = NULL; //Liberar memoria
}

bool pesquisar(char *nome){
    Celula *i = topo->prox;
    bool encontrado = false;
    while(!encontrado && (i != NULL)){
        if(strcmp(i->elemento.nome, nome) == 0) encontrado = true;
        else i = i->prox;
    }

    return encontrado;
}

void start(){
    topo = novaCelula();
}

int main(){
    start();//inicir pilha vazia
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

    fgets(nomeArquivo, i, stdin);
    int n = atoi(nomeArquivo);
    for(int j=0; j<n; j++){
        fgets(nomeArquivo, i, stdin);
        if(nomeArquivo[0] == 'R') printf("(R) %s\n", remover().nome);
        else{
            Series *nova = (Series*) malloc (sizeof(Series));
            lerSeries(nova);
            inserir(nova);

            free(nova); nova = NULL;
        }
    }
    mostrarRec();
    return 0;
}