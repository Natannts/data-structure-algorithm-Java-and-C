#include <stdio.h>
#include <stdlib.h>


double escreverInversamente(double *num){
    FILE *arq = fopen("pub.in","r"); //Abrindo o arquivo em modo leitura ("read"
    if (arq == NULL)
    {
        printf("[ERRO] Falha ao abrir o arquivo!\n");
        system("pause");
        return 0;
    }
    int i=0;
    while(!feof(arq)){
    fscanf(arq,"%lf", &num[i]);
    i++;//contar linhas
    }
    
    fclose(arq);

    for(int j=i-2; j>0; j--)
        printf("%0.6g\n",(double)num[j]);
    
    return 0;
}

int main(void){
    double num[1000];
    
    
        escreverInversamente(num);
        
  

    

    return 0;
}
