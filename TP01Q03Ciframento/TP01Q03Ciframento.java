class TP01Q03Ciframento{

	public static boolean isFim(String str){	
		return (str.length()==3 && str.charAt(0)=='F' && str.charAt(1) == 'I' && str.charAt(2) =='M');
	}

	public static void Ciframento(String str){
		String cripto = "" ;
		int chave = 3;
		for(int i=0; i<str.length();i++){
			cripto += (char)(str.charAt(i)+chave);
		}
		System.out.println(cripto);
	}

	public static void main (String[] args){

		String[] entrada = new String[1000];
		MyIO.setCharset("UTF-8");//ler caracteres do idioma

		int numEntrada = 0;

		//Leitura de entrada padrao
		do{
		entrada[numEntrada] = MyIO.readLine();
		}while(isFim(entrada[numEntrada++]) == false);
		numEntrada--;//Desconsidera a ultima linha que contem FIM
		
		//Para cada linha de entrada gera uma de saida
		for(int i=0; i<numEntrada; i++){
		Ciframento(entrada[i]);
		}



	}
}
