#include<stdio.h>
#include<string.h>

int inverter (char *str, int x, int aux){
    if (x <= aux ) return 1;
    else if (str[x] != str[aux] ) return 0;

    return inverter(str, x-1 , aux+1);
}

int palindromo(char* str){
	int aux1 , x1 = 0;

    aux1 = inverter(str, strlen(str)-1, x1);

    if(aux1 == 1){
			printf("SIM\n");			
		}
		else{
			printf("NAO\n");
		}
	
}

int main(){
	char str[1000];
	scanf("%[^\n]%*c", str); //le uma linha inteira (le tudo ate encontrar um \n, e tira ele)
	
	while(strcmp(str, "FIM")){ //roda enquanto nao for a palavra FIM

        palindromo(str);
		
		scanf("%[^\n]%*c", str);
	}	
}