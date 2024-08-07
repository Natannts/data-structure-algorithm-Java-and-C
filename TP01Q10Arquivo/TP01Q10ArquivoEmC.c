#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
int main ()
{
    int n = 0;  // numero de elementos que serao armazenados em out.txt
    scanf("%d",&n);
    FILE* arq = fopen("aut.txt","wb");
    double grava = 0.0;
    if (arq != NULL)
    {
        for(int i = 0 ; i < n; i++)
        {
            scanf("%lf",&grava);
            
            fwrite(&grava,sizeof(double),1,arq);
        }
    }else printf("ERROR :X");
    fclose(arq);
    FILE* arq1 = fopen("aut.txt","rb");
    double scan = 0.0;
    if (arq != NULL)
    {
            
        for (int i = 1; i <= n; i++)
        {
            fseek(arq1,-i*(sizeof(double)),SEEK_END);
            fread(&scan,sizeof(double),1,arq1);
            printf("%g\n",scan);
        }
    }
        fclose(arq1);

return 0;
}