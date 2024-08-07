class TP01Q05AlgebraBooleana{
	public static void main(String args[]){
		int numEntradas = MyIO.readInt(); //Numero de valores binarios que serao digitados a seguir
		while(!fim(numEntradas)){
			char entradas[] = new char[numEntradas];
            for(int i=0; i<numEntradas; i++){
                entradas[i] = MyIO.readChar(); //Ler cada um dos 'N' valores binarios
                MyIO.readChar(); //"Eliminando" os espacos (" ") depois de cada valor de entrada
            }
			String expressao = MyIO.readLine(); //Ler, entao, a expressao booleana que sera avaliada

			char result = calcValorExpressao(expressao, entradas, numEntradas); //Calcular o valor final da expressao booleana
			MyIO.println(result); //Escrever o resultado final (conforme o valor final da expressao avaliada)

			//Por fim, realizar a proxima leitura para "numEntradas":
			numEntradas = MyIO.readInt();
		}
	}

	static boolean fim(int n){
		boolean flag = false;
		if(n == 0) flag = true;
		return flag;
	}

	static char calcValorExpressao(String expressao, char entradas[], int numEntradas){
		int numTestes = calcNumTestes(expressao);

		//Usar um laco de repeticao para realizar todos os testes necessarios; um de cada vez:
		for(int i=0; i<numTestes; i++){
			int indicesProxTeste[] = new int[2]; //Onde [0] == inicio("(")  e  [1] == fim(")")  do proximo teste
			calcIndices(indicesProxTeste, expressao);
			boolean valorAtual = calcularProxTeste(indicesProxTeste, expressao, entradas);

			//Reduzir a expressao booleana (substituindo o teste realizado por seu resultado: 
			String aux = expressao; //Variavel auxiliar para facilitar a alteracao da String "expressao"
			expressao = reduzirExp(aux, valorAtual, indicesProxTeste);
		}

		//Definir o resultado conforme o valor final da expressao e, em seguida, retorna-lo:
		char valorFinal = expressao.charAt(0); //Quando chegar aqui, a expressao original ja tera sido reduzida para um unico char (corresponde ao resultado final)
		return valorFinal;
	}

	static int calcNumTestes(String expressao){
		int contador = 0; //Contar quantos parenteses fechando (")") existem na expressao - achando assim o numero de testes na mesma
		for(int i=0; i<expressao.length(); i++){
			if(expressao.charAt(i) == ')') contador++;
		}
		return contador;
	}

	static void calcIndices(int indicesProxTeste[], String expressao){
		int i=0; //Variavel de controle
		boolean proxTesteEncontrado = false; //Booleana de controle

		//Achar o PRIMEIRO parenteses fechando - ele corresponde ao proximo teste que deve ser feito:
		while(proxTesteEncontrado==false && i<expressao.length()){
			if(expressao.charAt(i) == ')'){
				proxTesteEncontrado = true;
				indicesProxTeste[1] = i;
			}
			else i++; //Usando "else" para evitar adicoes desnecessarias (quando encontrar o parenteses)
		}
		//"Re-setar" as variaveis de controle:
		i = (indicesProxTeste[1]-1); //"indicesProxTeste[1]-1" pois ja sabe-se que em "indicesProxTeste[1]" temos um ")"
		proxTesteEncontrado = false;

		//Agora, achar o parenteses abrindo mais proximo dele:
		while(proxTesteEncontrado==false && i>=0){
			if(expressao.charAt(i) == '('){
				proxTesteEncontrado = true;
				indicesProxTeste[0] = i;
			}
			else i--; //Novamente, usando "else" para evitar adicoes desnecessarias (quando encontrar o parenteses)
		}
	}

	static boolean calcularProxTeste(int indices[], String exp, char entradas[]){
		boolean teste;
		/** A ultima letra do operador logico define qual operador eh, sendo:
		 * 'r' referente ao operador logico "OR";
		 * 't' referente ao operador logico "NOT";
		 * 'd' referente ao operador logico "AND".
		*/
		char operador = exp.charAt(indices[0]-1);

		//Copiar os valores do teste da expressao booleana original para uma nova String ("valores"):
		String valores = "";
		for(int i=(indices[0]+1); i<indices[1]; i++){ //"i=(indices[0]+1)" e "i<indices[1]" para pular a leitura dos parenteses do teste
			//Ao mesmo tempo em que for copiar o teste, substituir 'A', 'B' ou 'C' pelas respectivas entradas:
			if(exp.charAt(i)=='A' || exp.charAt(i)=='B' || exp.charAt(i)=='C') valores += substituirEntradas(entradas, exp.charAt(i));
			else if(exp.charAt(i)=='0' || exp.charAt(i)=='1') valores += exp.charAt(i);
		}

		switch(operador){
			//Caso o operador seja um AND:
			case 'd':
				teste = operadorAnd(valores); //Chamar o respectivo metodo para testar
			break;
			//Caso o operador seja um OR:
			case 'r':
				teste = operadorOr(valores); //Chamar o respectivo metodo para testar
			break;
			//Caso o operador seja um NOT (unica possibilidade restante):
			default:
				teste = operadorNot(valores); //Chamar o respectivo metodo para testar
			break;
		}
		
		return teste;
	}

	static char substituirEntradas(char entradas[], char letra){
		char novo;
		if(letra == 'A') novo = entradas[0];
		else if(letra == 'B') novo = entradas[1];
		else novo = entradas[2]; //"letra == 'C'" eh a ultima possibilidade restante

		return novo;
	}

	static boolean operadorAnd(String valores){
		int i=0; //Variavel de controle
		boolean result = true;
		while(i<valores.length() && result==true){
			if(valores.charAt(i) == '0') result = false; //No operador AND, todos os valores testados devem ser verdadeiros.
			else i++;
		}
		return result;
	}

	static boolean operadorOr(String valores){
		boolean result = false;
		int i=0; //Variavel de controle
		while(i<valores.length() && result==false){
			if(valores.charAt(i) == '1') result = true; //No operador OR, ao menos um dos valores deve ser verdadeiro.
			else i++;
		}
		return result;
	}

	static boolean operadorNot(String valores){
        boolean result = false;
        //Nao eh preciso usar nenhum laco de repeticao, por que o operador NOT trabalha com apenas um valor:
		if(valores.charAt(0) == '0') result = true; //O operador NOT apenas troca o valor booleano do valor em questao.
		return result;
	}

	static String reduzirExp(String exp, boolean valorAtual, int indices[]){
		String reduzida = "";
		int tamOperador = 3; //Tanto AND quanto NOT possuem tamanho = 3 chars.
		if(exp.charAt(indices[0]-1) == 'r') tamOperador = 2; //Se o operador for OR, setar tamanho para 2 chars.

		//Copiar a parte da string original ANTES do teste realizado:
		for(int i=0; i<(indices[0] - tamOperador); i++){
			reduzida += exp.charAt(i);
		}

		//Substituir a parte do teste por seu resultado:
		if(valorAtual == true) reduzida += "1";
		else reduzida += "0";

		//Finalmente, copiar o restante da string original:
		for(int i=indices[1]+1; i<exp.length(); i++){
			reduzida += exp.charAt(i);
		}

		return reduzida;
	}
}
