class TP01Q01Palindromo {

   public static boolean isFim(String s){
      return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static boolean isPalindromo (String s){
   
    	boolean resp = true;

//	MyIO.println(s);

  	for(int i = 0; i < s.length() ; i++){	       
//         MyIO.println(s.charAt(i) + "\t" + s.charAt(s.length()-1-i));	 

	 if(s.charAt(i) != s.charAt(s.length()-1-i) ){
            resp = false;
        }
      }
      return resp;
   }

   public static void main (String[] args){
      String[] entrada = new String[1000];
      MyIO.setCharset("UTF-8");

      int numEntrada = 0;

      //Leitura da entrada padrao
      do {
         entrada[numEntrada] = MyIO.readLine();
      } while (isFim(entrada[numEntrada++]) == false);
      numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
      

      //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
      for(int i = 0; i < numEntrada; i++){

         if ( isPalindromo(entrada[i]) == true ){
		 MyIO.println("SIM");
	 }else{
		 MyIO.println("NAO");
	 }
      }
   }
}


