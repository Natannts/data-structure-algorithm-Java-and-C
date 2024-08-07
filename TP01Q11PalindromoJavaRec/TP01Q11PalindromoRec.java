public class TP01Q11PalindromoRec{
    public static void main(String[] args){
        String str = MyIO.readLine();
        while(!fim(str)){
            String resposta = verifPalindromo(str); //Chamar o metodo recursivo
            MyIO.println(resposta); //Imprimir o resultado
            str = MyIO.readLine(); //Por fim, realizar a proxima leitura
        }
    }

    static boolean fim(String str){
        return (str.length()==3 && str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M');
    }

    static String verifPalindromo(String str){
        String result = "SIM";
        if(verifPalindromo(str, 0) == false) result = "NAO";
        return result;
    }

    static boolean verifPalindromo(String str, int i){
        boolean palindromo = true;
        if(palindromo && i<str.length()/2){ //Caso base implicito
            if(str.charAt(i) != str.charAt(str.length()-1-i)) palindromo = false; //Se em algum momento houver diferencas, interromper o metodo
            else palindromo = verifPalindromo(str, i+1);
        }
        return palindromo;
    }
}