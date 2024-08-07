import java.io.*;
public class Arquivo {
	/*
	 * format - nao sei se e defeito ou intencional, mas o pub.in e pub.out estao formatados de maneira diferente, esse metodo conserta a formatacao
	 */
	public static String format(double d) {
		if((int)d == d) {//verifica se o numero e inteiro
			return String.valueOf((int)d);//se for inteiro, retorna uma formatacao de um int (nao contem '.' nem ',')
		} else {
			return String.valueOf(d);//caso contrario retorna uma formatacao de um float com apenas quantidades de 0 necessarias
		}
	}

	public static void main(String[] args) {
		//escreve tudo para um arquivo
		int count = MyIO.readInt();
		Arq.openWrite("a.dat");
		for(int i = 0; i < count; i++) {
			Arq.println(format(MyIO.readDouble()));
		}
		Arq.close();
		//le ele de tras para frente
		try {
			RandomAccessFile raf = new RandomAccessFile(new File("a.dat"), "r");
			long len = raf.length();

			raf.seek(len-2);//pula para o final, logo antes do ultimo \n
			// ... z y \n a b c d \n
			//                  ^
			//                  posicao do ponteiro

			do {
				int current;
				//le de tras para frente ate encontrar um \n
				do {
					current = raf.read();
					// ... z y \n a b c d \n
					//                     ^
					//                     posicao do ponteiro (leu o 'd')
					if(current != '\n') { //se nao encontrou o \n, checa o caractere de tras
						if(raf.getFilePointer()==1) {//se o ultimo caractere lido for o primeiro do arquivo, volta apenas um byte pois nao ha um \n antes desse byte
							raf.seek(raf.getFilePointer()-1);
						} else {
							raf.seek(raf.getFilePointer()-2);//volta o ponteiro para o byte de tras, antes de ser avancado pelo read
							// ... z y \n a b c d \n
							//                ^
							//                posicao do ponteiro
						}
					}
				} while(current != '\n' && raf.getFilePointer()>0);

				//atingiu um \n lendo de tras para frente
				//salva esse indice
				long pos = raf.getFilePointer();
				// ... z y \n a b c d \n
				//            ^
				//            posicao do ponteiro
				do {//le para frente ate encontrar um \n, printando todos os caracteres em ordem
					current = raf.read();
					// ... z y \n a b c d \n
					//              ^
					//              posicao do ponteiro (leu o 'a')
					MyIO.print((char)current);//printa o 'a' nesse caso
				} while(current != '\n'); //continua ate achar um \n (ele e printado tambem)
				raf.seek(pos-2);//vai para posicao logo antes do \n encontrado, para repetir os mesmos passos com a linha anterior
			} while(raf.getFilePointer() > 0);//repete ate chegar ao inicio
		} catch(Exception e) {

		}
	}
}