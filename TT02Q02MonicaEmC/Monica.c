#include <stdio.h>

int monica(int M, int P, int S){
  int T = M - P - S;
  int velho = 0;
  if(T>P && T>S) printf("%d",T);
  else if (P>T && P>S) printf("%d",P);
  else printf("%d",S);
  return 0;
}

int main(void) {
  int M =0, P=0, S=0;
  scanf("%d",&M);
  scanf("%d",&P);
  scanf("%d",&S);
  monica(M,P,S);
  return 0;
}