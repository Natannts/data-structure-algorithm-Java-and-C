import java.util.Random;

class TP01Q04Aleatorio{

	public static boolean isFim (String s){
	return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}

	public static String Troca(String s, Random rand){
				
		char a = (char) ('a' + ((Math.abs(rand.nextInt())%26)));
		char b = (char) ('a' + ((Math.abs(rand.nextInt())%26)));
		String res ="";
	


		for(int i=0; i< s.length(); i++){
			if(s.charAt(i) == a){
				res += b;
			}else{
				res += s.charAt(i);
			}
		}
		return res;
	}
	
	public static void main (String[] args){
		Random rand = new Random();
		rand.setSeed(4);
		String[] entrada = new String[1000];
		MyIO.setCharset("UTF-8");

		int numEntrada =0;

		do{
			entrada[numEntrada] = MyIO.readLine();
		}while (isFim(entrada[numEntrada++]) == false);
		numEntrada--; //Desconsidera ultima linha contendo a palavra FIM

		//Para cada linha de entrada uma de linha de saida
		for(int i=0; i<numEntrada; i++){
			MyIO.println(Troca(entrada[i],rand));
		}
	}
}	
