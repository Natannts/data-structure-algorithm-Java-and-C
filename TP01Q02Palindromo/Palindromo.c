#include <stdio.h>
#include <string.h>

int palindromo(char* str){
	int indice = 0;

	while(str[indice] != '\0') {//continua ate achar o null terminator para contar quantos caracteres tem
		indice++;
	}

	for(int i = 0; i < indice/2; i++) {
		if(str[i] != str[indice-1-i]) {//comparacao espelhada, se falhar aborta e retorna 0
			return 0;
		}
	}
	return 1;
}

int main(){
	char str[1000];
	scanf("%[^\n]%*c", str); //le uma linha inteira (le tudo ate encontrar um \n, e tira ele)
	
	while(strcmp(str, "FIM")){ //roda enquanto nao for a palavra FIM

		if(palindromo(str)){
			printf("SIM\n");			
		}
		else{
			printf("NAO\n");
		}
		scanf("%[^\n]%*c", str);
	}	
}