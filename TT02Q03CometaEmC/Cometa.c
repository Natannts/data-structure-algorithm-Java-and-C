#include <stdio.h>

int cometa(int ano){
	int n = ((ano-10)/76)+1;
  int prox = (n*76+10);
	printf("%d",prox);
  return 0;
}

int main(){
	int ano=0;
	scanf("%d",&ano);

	cometa(ano);
}