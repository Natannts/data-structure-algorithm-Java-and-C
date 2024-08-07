#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isVogal(char* str);
bool isConsoante(char* str);
bool isInteiro(char* str);
bool isReal(char* str);
bool isFim(char* s);


bool isVogal(char* str){
	char vogais[] =  {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
	for(int i = 0; i < strlen(str); i++){
		bool isVogal = false;
		for(int j=0; j < strlen(vogais); j++){
			if(str[i] == vogais[i]){
				isVogal = true;
			}
		}
		if(isVogal == false){
			return false;
		}
	}

	return true; 
}

bool isConsoante(char* str){
	char vogais[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
		for(int i = 0; i < strlen(str); i++){
			for(int j = 0; j < strlen(vogais); j++){
				if((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z')){//conferir se é letra
					if(str[i] == vogais[j]){//conferir se é vogal, e se for ja retorna false 
						return false;
					}	
				}
				else{
					return false;//se nao for letra ja retorna false tambem
				}
			}
		}

		return true;//se passar em todos os testes é porque e consoante 

}


bool isInteiro(char* str){
	for(int i = 0; i < strlen(str); i++){
		if(str[i] < '0' || str[i] > '9'){
			return false;
		}
	}

	return true;
}

bool isReal(char* str){
	int cont = 0; //contador de ponto e virgula
	bool real = false;
	for(int i = 0; i < strlen(str); i++){
		if(str[i] == '.' || str[i] == ','){
			cont++;
		}
		else if(str[i] < '0' || str[i] > '9'){
			return false;
		}
	}

	if(cont < 2){
		real = true;
	}

	return real;
}


bool isFim(char* s){
   return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}


int main(){
	char str[1000];
	scanf("%[^\n]%*c", str);

	while(isFim(str) == false){
		if(isVogal(str)){
			printf("%s", "SIM");
		}
		else{
			printf("%s", "NAO");
		}

		if(isConsoante(str)){
			printf("%s", " SIM");
		}
		else{
			printf("%s", " NAO");
		}

		if(isInteiro(str)){
			printf("%s", " SIM");
		}
		else{
			printf("%s", " NAO");
		}

		if(isReal(str)){
			printf("%s", " SIM\n");
		}
		else{
			printf("%s", " NAO\n");
		}


		

		
		scanf("%[^\n]%*c", str);
	}
}	