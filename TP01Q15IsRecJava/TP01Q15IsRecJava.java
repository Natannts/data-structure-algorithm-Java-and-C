public class TP01Q15IsRecJava{
    public static void main(String args[]){
        String str = MyIO.readLine();
        while(!fim(str)){
            if(soVogais(str)) MyIO.println("SIM NAO NAO NAO"); //Se forem somente vogais, todos os outros sao falsos obrigatoriamente.
            else if(soConsoantes(str)) MyIO.println("NAO SIM NAO NAO"); //Se forem somente consoantes, todos os outros resultados sao falsos obrigatoriamente.
            else if(numInteiro(str)) MyIO.println("NAO NAO SIM SIM"); //Se formar um numero inteiro, obrigatoriamente, forma tambem um numero real.
            else if(numReal(str)) MyIO.println("NAO NAO NAO SIM"); //Se chegou ate aqui, todos os outros sao falsos.
            else MyIO.println("NAO NAO NAO NAO"); //Se chegou ate aqui, todos o resultados sao falsos.
            //Por fim, realizar a proxima leitura:
            str = MyIO.readLine();
        }
    }

    static boolean fim(String str){
        boolean flag = false;
        if(str.length()==3 && str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M') flag = true;
        return flag;
    }

    static boolean soVogais(String str){
        return soVogais(0, str); //Inicializando o contador para a recursividade
    }

    static boolean soVogais(int i, String str){
        boolean soVogais = true;
        if(i<str.length()){
            if(!vogal(str.charAt(i))) soVogais = false;
            else soVogais = soVogais(i+1, str); //Verificar o proximo teste apenas caso nao tenha falhado:
        }
        return soVogais;
    }

    static boolean vogal(char c){
        return (c=='a' || c=='A' || c=='e' || c=='E' || c=='i' || c=='I' || c=='o' || c=='O' || c=='u' || c=='U');
    }

    static boolean soConsoantes(String str){
        return soConsoantes(0, str); //Inicializando o contador para a recursividade
    }

    static boolean soConsoantes(int i, String str){
        boolean soConsoantes = true;
        if(i<str.length()){
            if(!consoante(str.charAt(i))) soConsoantes = false;
            else soConsoantes = soConsoantes(i+1, str); //Verificar o proximo teste apenas caso nao tenha falhado:
        }
        return soConsoantes;
    }

    static boolean consoante(char c){
        return (vogal(c)==false && ( (c>='a' && c<='z') || (c>='A' && c<='Z') )); //Garantir que eh uma letra; e que esta nao eh uma vogal.
    }

    static boolean numInteiro(String str){
        boolean ehNumInteiro;
        if(!numero(str.charAt(0)) && str.charAt(0)!='-') ehNumInteiro = false; //Verificando o primeiro char separadamente - para caso seja num negativo
        else ehNumInteiro = numInteiro(1, str); //Inicializando o contador para a recursividade (iniciado em 1 pois ja foi testado o primeiro char)
        return ehNumInteiro;
    }

    static boolean numero(char c){
        return c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9' || c=='0';
    }

    static boolean numInteiro(int i, String str){
        boolean ehNumInteiro = true;
        if(i<str.length()){
            if(!numero(str.charAt(i))) ehNumInteiro = false;
            else ehNumInteiro = numInteiro(i+1, str); //Verificar o proximo teste apenas caso nao tenha falhado:
        }
        return ehNumInteiro;
    }

    static boolean numReal(String str){
        boolean ehNumReal = true;
        boolean jaTemVirgula = false;
        //Verificando o primeiro char separadamente:
        if(!numero(str.charAt(0)) && str.charAt(0) != '-'){
            if(str.charAt(0)=='.' || str.charAt(0)==',') jaTemVirgula = true; //Caso seja a representacao apenas dos nums fracionarios)
            else ehNumReal = false;
        }
        if(ehNumReal==true) ehNumReal = numReal(1, str, jaTemVirgula); //Inicializando o contador para a recursividade (iniciado em 1 pois ja foi testado o primeiro char)
        return ehNumReal;
    }

    static boolean numReal(int i, String str, boolean jaTemVirgula){
        boolean ehNumReal = true;
        if(i<str.length()){
            if(!numero(str.charAt(i))){
                if((str.charAt(i)=='.' || str.charAt(i)==',') && jaTemVirgula==false) jaTemVirgula = true;
                else ehNumReal = false;
            }
            if(ehNumReal == true) ehNumReal = numReal(i+1, str, jaTemVirgula); //Verificar o proximo teste apenas caso nao tenha falhado:
        }
        return ehNumReal;
    }
}