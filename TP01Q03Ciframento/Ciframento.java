public class Ciframento{

    /*
     * ciframento - retorna uma String com o ciframento de Cesar com chave 3. Itera todos os caracteres da String original de entrada e concatena eles com o codigo ascii aumentado em 3
     */
    public static String ciframento(String in){
        String result = "";//String vazia para concatenar durante a iteracao
        int key = 3;//chave de ciframento
        for(int i = 0; i < in.length(); i++) {
            result += (char)(in.charAt(i)+key); //concatena o caractere atual da string original, mas com 3 adicionado ao codigo ascii dele 
        }
        
        return result;

    }

    /*
     * compare - retorna true se duas strings forem identicas
     */
    public static  boolean compare(String a, String b) {
        if(a.length() != b.length()) {
            return false;//aborta com false se tamanhos forem diferentes
        }
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                return false;//se um caractere nao der match pode retornar falso
            }
        }
        return true;//nenhum teste falhou, strings sao iguais
    }

    public static void main(String[] args) {

        String str = MyIO.readLine();
        MyIO.setCharset("UTF-8");       

        while(!compare(str, "FIM")){

            MyIO.println(ciframento(str));
            str = MyIO.readLine();
        }
    }
}