public class TP01Q13CiframentoRec{
    static int chaveCiframento = 3;
    public static void main(String args[]){
        String str = MyIO.readLine();
        while(!fim(str)){
            String result = criptografarMensagem(str); //Chamar o metodo recursivo
            MyIO.println(result); //Imprimir a mensagem criptografada
            str = MyIO.readLine(); //Realizar a proxima leitura
        }
    }

    static boolean fim(String str){
        boolean flag = false;
        if(str.length()==3 && str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M') flag = true; //"==3" para nao interromper a leitura caso haja uma palavra que inicie com "FIM" - por exemplo, "FIMOSE".
        return flag;
    }

    static String criptografarMensagem(String str){
        return criptografarMensagem(str, 0); //Iniciando a variavel de controle para a recursividade
    }

    static String criptografarMensagem(String str, int i){
        String criptografada = "";
        if(i<str.length()){ //Caso base implicito
            criptografada += ((char)(str.charAt(i) + chaveCiframento));
            criptografada += criptografarMensagem(str, i+1);
        }

        return criptografada;
    }
}