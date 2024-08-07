class TP01Q06Is{
	public static boolean isVogal(String str){
		char [] vogais = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
		for(int i = 0; i < str.length(); i++){
			boolean isVogal = false;
			for(int j = 0; j < vogais.length; j++){
				if(str.charAt(i) == vogais[j]){
					isVogal = true;
				}
			}
			if(!isVogal){ // se ALGUM caracter QUALQUER (nesse caso o atual) nao bater com uma vogal QUALQUER, a String contem algo alem de vogais e retorna falso
				return false;
			}
		}
		return true;	
	}

	public static boolean isConsoante(String str){
		char [] vogais = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
		for(int i = 0; i < str.length(); i++){
			for(int j = 0; j < vogais.length; j++){
				if((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')){//conferir se é letra
					if(str.charAt(i) == vogais[j]){//conferir se é vogal, e se for ja retorna false 
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

	public static boolean isInteiro(String str){
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) < '0'|| str.charAt(i) > '9'){//se der true essa condiçao, nao é numero entao ja retorna false
				return false;
			}
		}

		return true;
	}

	public static boolean isReal(String str){
		int cont = 0; //contador de ponto e virgula
		boolean real = false;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == '.' || str.charAt(i) == ','){
				cont++;
			} 
			else if(str.charAt(i) < '0' || str.charAt(i) > '9'){ //se nao for numero (e nao for ponto ou virgula) ja pode retornar falso
				return false;
			}
		}

		if(cont <2){
			real = true; //se passar nos testes de numero e ponto e virgula so pode retornar true se o numero tiver apenas um ou nenhum ponto e virgula
		}

		return real;
	}


	public static  boolean compare(String a, String b){
		if(a.length() != b.length()){
			return false;//já aborta com false se tamanhos forem diferentes
		}
		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) != b.charAt(i)){
				return false;//se um caractere nao der match pode retornar falso
			}
		}
		return true;//nenhum teste falhou, strings sao iguais
	}


	public static void main(String [] args){
		String str = MyIO.readLine();
		
		while(!compare(str, "FIM")){
			if(isVogal(str)){
				System.out.print("SIM ");
			}
			else{
				System.out.print("NAO ");
			}

			if(isConsoante(str)){
				System.out.print("SIM ");
			}
			else{
				System.out.print("NAO ");
			}

			if(isInteiro(str)){
				System.out.print("SIM ");
			}
			else{
				System.out.print("NAO ");
			}

			if(isReal(str)){
				System.out.print("SIM \n");
			}
			else{
				System.out.print("NAO \n");
			}

			str = MyIO.readLine();
		}	
	}
}//end class