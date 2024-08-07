public class TP01Q14AlgebraBooleanaRec{
    public static void main(String args[]){
        int numEntradas = MyIO.readInt();
        while(!fim(numEntradas)){
            //Ler as entradas:
            char entradas[] = new char[numEntradas]; 
            for(int i=0; i<numEntradas; i++){
                entradas[i] = MyIO.readChar();
                MyIO.readChar(); //Descartando o " " entre cada valor de entrada
            }
            //Ler a expressao e aplicar as entradas onde necessario:
            String expressao = lerExpressaoESubstituirEntradas(entradas);
            //Calcular o numero de testes que serao necessarios realizar:
            int numTestes = calcNumTestes(expressao);
            //Chamar o metodo recursivo que calculara o valor da expressao:
            calcExpressao(numTestes, expressao); //O propio metodo escreve o resultado final

            numEntradas = MyIO.readInt(); //Realizar a proxima leitura
        }
    }

    static boolean fim(int n){
        return n==0;
    }

    static String lerExpressaoESubstituirEntradas(char []entradas){
        String temp = MyIO.readLine(); //O resto da entrada corresponde a expressao booleana
        String expressao = "";
        //Copiar a expressao lida, substituindo as entradas onde for o caso:
        for(int i=0; i<temp.length(); i++){
            if(temp.charAt(i)!='A' && temp.charAt(i)!='B' && temp.charAt(i)!='C') expressao += temp.charAt(i);
            else if(temp.charAt(i) == 'A') expressao += entradas[0];
            else if(temp.charAt(i) == 'B') expressao += entradas[1];
            else expressao += entradas[2]; //O unico caso restante eh se for a 'C'
        }
        return expressao;
    }

    static int calcNumTestes(String exp){
        int contador = 0;
        for(int i=0; i<exp.length(); i++){
            if(exp.charAt(i) == ')') contador++;
        }
        return contador;
    }

    static void calcExpressao(int numTestes, String exp){
        calcExpressao(exp, numTestes, 0); //Inicializando o contador para a recursividade
    }

    static void calcExpressao(String exp, int numTestes, int testesRealizados){
        String resultadoFinal = "";
        if(testesRealizados < numTestes){
            exp = resolverProximoTeste(exp); //Calcular o resultado do proximo teste (retorna a string reduzida - onde o teste feito eh substituido pelo resultado do mesmo)
            //Chamar o metodo recursivamente; enviando a nova expressao e aumentandor o contador:
            calcExpressao(exp, numTestes, testesRealizados+1);
        }

        else{ //Imprimir resultado final (quando todos os testes tiverem sido realizados, a String "exp" sera o propio resultado final):
            MyIO.println(exp.charAt(0)); //Coloquei "exp.chatAt(0)" pois as entradas das linhas 49-56 tinham um " " no final da string
        }
    }

    static String resolverProximoTeste(String exp){
        boolean resultTesteAtual;
        //Definir onde esta o proximo teste, e tambem qual o operador dele (AND, NOT ou OR)
        int indicesProxTeste[] = new int[2]; //Armazena onde inicia ("(") e onde termina (")") o proximo teste a ser feito
        char operador = definirProximoTeste(indicesProxTeste, exp);
        //Calcular prox teste:
        resultTesteAtual = resultadoProxTeste(exp, indicesProxTeste, operador);
        //Re-escrever a expressao (retirando o teste ja realizado):
        exp = reduzirExpressao(exp, operador, indicesProxTeste, resultTesteAtual);

        return exp;
    }

    static boolean resultadoProxTeste(String exp, int indicesProxTeste[], char operador){
        boolean result = false;
        if(operador == 't') result = operacaoNot(exp.charAt(indicesProxTeste[0]+1)); //O operador NOT possui apenas um valor dentro; entao nao precisa-se separar os valores dentro do parenteses
        else{
            String valoresSeparados = separarValores(exp, indicesProxTeste);
            if(operador == 'd') result = operacaoAnd(valoresSeparados);
            else result = operacaoOr(valoresSeparados); //O operador OR eh a ultima possibilidade restante
        }
        return result;
    }

    static String separarValores(String exp, int indicesProxTeste[]){
        String valores = "";
        //Copiar apenas os VALORES do teste atual para a nova string:
        for(int i=indicesProxTeste[0]; i<indicesProxTeste[1]; i++){
            if(exp.charAt(i)=='1' || exp.charAt(i)=='0') valores += exp.charAt(i);
        }
        return valores;
    }

    static char definirProximoTeste(int indicesProxTeste[], String exp){ //Define onde esta o proximo teste, e tambem qual o operador dele (AND, NOT ou OR)
        int i=0; //Variavel de controle
        boolean encontrado = false; //Booleana de controle (para interromper o laco while quando o que procura-se for encontrado)
        //Primeiro, achar o primeiro parenteses fechando (ele corresponde ao fim do proximo teste que deve-se realizar)
        while(i<exp.length() && encontrado==false){
            if(exp.charAt(i) == ')'){
                encontrado = true;
                indicesProxTeste[1] = i;
            }
            else i++;
        }
        //Agora, "re-settar" as variaveis de controle e achar onde esse teste inicia ("("):
        i = indicesProxTeste[1];
        encontrado = false;
        while(i>=0 && encontrado==false){
            if(exp.charAt(i) == '('){
                encontrado = true;
                indicesProxTeste[0] = i;
            }
            else i--;
        }
        //Retornar qual o operador
        return exp.charAt(indicesProxTeste[0]-1); //A ultima letra antes do parenteses define o operador ('d' = and; 't' = not; 'r' = or)
    }

    static boolean operacaoNot(char valor){
        boolean result = true;
        if(valor == '1') result = false;
        return result;
    }

    static boolean operacaoAnd(String valoresSeparados){
        boolean result = true;
        int i=0; //Variave de controle
        while(i<valoresSeparados.length() && result == true){
            if(valoresSeparados.charAt(i)=='0') result = false;
            else i++;
        }
        return result;
    }

    static boolean operacaoOr(String valoresSeparados){
        boolean result = false;
        int i=0; //Variavel de controle
        while(result==false && i<valoresSeparados.length()){
            if(valoresSeparados.charAt(i) == '1') result = true;
            else i++;
        }
        return result;
    }

    static String reduzirExpressao(String exp, char operador, int indicesProxTeste[], boolean resultTesteAtual){
        String expReduzida = "";
        int tamOperador = 3;
        if(operador == 'r') tamOperador = 2;
        //Copiar ate o inicio do teste ja realizado (incluindo o operador):
        for(int i=0; i<(indicesProxTeste[0]-tamOperador); i++){
            expReduzida += exp.charAt(i);
        }
        //Escrever o resultado (1 ou 0) no lugar do teste feito:
        if(resultTesteAtual == true) expReduzida += '1';
        else expReduzida += '0';
        //Copiar o restante da expressao:
        for(int i=(indicesProxTeste[1]+1); i<exp.length(); i++){ //Nao copiar o parenteses (por isso "indicesProxTeste[1]+1")
            expReduzida += exp.charAt(i);
        }
        return expReduzida;
    }
}