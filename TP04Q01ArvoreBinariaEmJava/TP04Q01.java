import java.util.*;
import java.lang.*;
//import java.io.RandomAccessFile;

class Series{
    private String nome;
    private String idioma;
    private String formato;
    private String duracao;
    private String paisOrigem;
    private String emissoraOriginal;
    private String transmicaoOriginal;
    private int qntTemporadas;
    private int qntEpisodios;

    //construtor
    Series(){
        nome ="";
        formato = "";
        duracao = "";
        paisOrigem = "";
        idioma = "";
        emissoraOriginal = "";
        transmicaoOriginal = "";
        qntTemporadas = 0;
        qntEpisodios = 0;
    }

    //construtor
    Series (String nome, 
            String formato, String duracao,
            String paisOrigem, String idioma,
            String emissoraOriginal, String transmicaoOriginal,
            int qntTemporadas, int qntEpisodios){

        this.nome = nome;
        this.formato = formato;
        this.duracao = duracao;
        this.paisOrigem = paisOrigem;
        this.idioma = idioma;
        this.emissoraOriginal = emissoraOriginal;
        this.transmicaoOriginal = transmicaoOriginal;
        this.qntTemporadas = qntTemporadas;
        this.qntEpisodios = qntEpisodios;
    }

    //set(s) e get(s)
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setIdioma(String idioma){
        this.idioma = idioma;
    }

    public String getIdioma(){
        return idioma;
    }

    public void setFormato(String formato){
        this.formato = formato;
    }

    public String getFormato(){
        return formato;
    }

    public void setDuracao(String duracao){
       this.duracao = duracao;
    }

    public String getDuracao(){
        return duracao;
    }

    public void setPaisOrigem(String paisOrigem){
        this.paisOrigem = paisOrigem;
    }

    public String getPaisOrigem(){
        return paisOrigem;
    }

    public void setEmissoraOriginal(String emissoraOriginal){
        this.emissoraOriginal = emissoraOriginal;
    }

    public String getEmissoraOriginal(){
        return emissoraOriginal;
    }

    public void setTransmicaoOriginal(String transmicaoOriginal){
        this.transmicaoOriginal = transmicaoOriginal;
    }

    public String getTransmicaoOriginal(){
        return transmicaoOriginal;
    }

    public void setQntTemporadas(int qntTemporadas){
        this.qntTemporadas = qntTemporadas;
    }

    public int getQntTemporadas(){
        return qntTemporadas;
    }

    public void setQntEpisodios(int qntEpisodios){
        this.qntEpisodios = qntEpisodios;
    }

    public int getQntEpisodios(){
        return qntEpisodios;
    }


    /**
	* Metodo de leitura do html
	* @param entrada Recebe o nome da pagina html
	*/ 
    public void ler(String arquivo) throws Exception{
        String[] vetor = new String[3000];
        String aux = "", endereco = ("/tmp/series/"+arquivo);
        int linha = 0;
    
        Arq.openRead(arquivo,"UTF-8");

        String name = arquivo.replace("_", " ").replace(".html", "");
		setNome(name);

        try{

            while(Arq.hasNext()){
                vetor[linha] = Arq.readLine();
                aux = vetor[linha].replaceAll("<.*?>" ,"");
                
                if(aux.equals("Formato"))
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
					setFormato(aux);
                    
				}
			 	else if(aux.equals("Duração") && linha < 200){
                    vetor[linha] = Arq.readLine();
                    aux = vetor[linha].replaceAll("<.*?>" ,"");
                    setDuracao(aux);
                    
                }
                else if(aux.equals("País de origem"))
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
                    aux = aux.replaceAll("&#160;", "");
					aux = aux.replaceAll("&nbsp;", "");
					setPaisOrigem(aux);
                    
                }
                else if(aux.equals("Idioma original"))//olhar espaços antes do idioma
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
					setIdioma(aux);
                   
				}
                else if(aux.equals("Emissora de televisão original"))//olhar espaços antes
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
					setEmissoraOriginal(aux);
                    
				}
                else if(aux.equals("Transmissão original"))
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
                    aux = aux.replaceAll("&#160;", "");
					aux = aux.replaceAll("&nbsp;", "");
					setTransmicaoOriginal(aux);
					
				}
                else if(aux.equals("N.º de temporadas") == true)
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
					String []ntemp = aux.split(" ");
					setQntTemporadas(Integer.parseInt(ntemp[0]));
                    
				}
                else if(aux.equals("N.º de episódios") == true)
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
					String []number = aux.split(" ");
					if((number[0].charAt(0) >= '0') && (number[0].charAt(0) <= '9'))
					{
						setQntEpisodios(Integer.parseInt(number[0]));
					}//end if
				}//end if
                
                linha++;
            }        
        }catch (Exception excecao){
                excecao = new Exception("Houve algum erro na leitura do arquivo!");
            }
        Arq.close();
    }

    //imprimir
    public void imprimir()
		{
		  	System.out.println("" + nome + " " + formato + 
				     " " + duracao + " " + paisOrigem + 
				     " " + idioma + 
                     " " + emissoraOriginal + 
				     " " + transmicaoOriginal + 
				     " " + qntTemporadas + 
				     " " + qntEpisodios);
		}//end imprimir()

        /**
		 * Metodo clone
		 * @return Retorna um ponteiro com todos os atributos clonados
		 */
		public Series clone()
		{
			Series series = new Series(this.nome, 
                                        this.formato, 
                                        this.duracao, 
                                        this.paisOrigem,
                                        this.idioma,
                                        this.emissoraOriginal,
                                        this.transmicaoOriginal,
                                        this.qntTemporadas,
                                        this.qntEpisodios);
			return(series);
		}//end clone()
}

/**
 * Classe No
 */
class No{
    Series elemento;
    No esq, dir;

    //Construtores
    No(){
        this.elemento = new Series();
        this.esq = this.dir = null;
    }
    No(Series elemento){
        this.elemento = elemento;
        this.esq = this.dir = null;
    }
}

class ArvoreBinaria{
    No raiz;
    static int numComparacoes = 0;
    static double tempoExec;

    //construtor
    ArvoreBinaria(){
        this.raiz = null;
    }

    //Metodos

    /**
     * Insercao
     * @param s elemento (series) a ser inserido
     * @throws Exception caso o elemento ja esteja inserido na arvore
     */
    public void inserir(Series s) throws Exception{
        this.raiz = inserir( s, raiz);//recursividade inicial
    }
    private No inserir(Series s, No i) throws Exception{
        if(i == null) i = new No(s); // localiza o local de insercao

        else if (s.getNome().compareTo(i.elemento.getNome() ) < 0){
            i.esq = inserir(s, i.esq);
            numComparacoes++;
        }
        else if (s.getNome().compareTo(i.elemento.getNome() ) > 0){
           numComparacoes++; 
           i.dir = inserir(s, i.dir);
        }else{
            throw new Exception("ERRO AO INSERIR!!");
        }
        return i;
    }

    /**
     * Remocao
     * @param s elemento (series) a ser inserido
     * @throws Exception caso o elemento ja esteja inserido na arvore
     */
    private void remover(String s) throws Exception{
        if(raiz == null) throw new Exception("ERRO AO REMOVER  !!!");

        else if(s.compareTo(raiz.elemento.getNome()) < 0){
            remover(s, raiz.esq, raiz);
            ArvoreBinaria.numComparacoes++;
        }else if(s.compareTo(raiz.elemento.getNome()) > 0){
            remover(s, raiz.dir, raiz);
            ArvoreBinaria.numComparacoes++;
        }else if (raiz.dir == null){
            raiz = raiz.esq;
            ArvoreBinaria.numComparacoes++;
        }else if (raiz.esq == null){
            raiz = raiz.dir;
            ArvoreBinaria.numComparacoes++;
        }else{
            raiz.esq = maiorEsq(raiz, raiz.esq);
        }
    }
    private void remover(String s, No i, No pai) throws Exception{
        if(i == null) throw new Exception("ERRO AO REMOVER  !!!");

        else if(s.compareTo(i.elemento.getNome()) < 0){
            remover(s, i.esq, i);
            ArvoreBinaria.numComparacoes++;
        }else if(s.compareTo(i.elemento.getNome()) > 0){
            remover(s, i.dir, i);
            ArvoreBinaria.numComparacoes++;
        }else if (i.dir == null){
           pai = i.esq;
            ArvoreBinaria.numComparacoes++;
        }else if (i.esq == null){
            pai = i.dir;
            ArvoreBinaria.numComparacoes++;
        }else{
            i.esq = maiorEsq(i, i.esq);
        }
    }

    /**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda.
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No maiorEsq(No i, No j) {

        // Encontrou o maximo da subarvore esquerda.
          if (j.dir == null) {
              i.elemento = j.elemento; // Substitui i por j.
              j = j.esq; // Substitui j por j.ESQ.
  
        // Existe no a direita.
          } else {
           // Caminha para direita.
              j.dir = maiorEsq(i, j.dir);
          }
          return j;
      }

    /**
	 * Metodo para pesquisar a existencia de um dado elemento (Serie) na arvore; bem como mostrar o caminho percorrido para faze-lo.
	 * @param nome chave de pesquisa do elemento
	 * @return TRUE se o elemento existir na arvore; FALSE se nao
	 */
    public boolean pesquisar(String nome){
        System.out.print("raiz ");
        return pesquisar(nome, raiz);
    }
    private boolean pesquisar(String nome, No i){
        boolean encontrado;

        if(i == null) encontrado = false;
        else if(nome.compareTo(i.elemento.getNome()) < 0){
            System.out.print("esq ");
            encontrado = pesquisar(nome,i.esq);
        }else if(nome.compareTo(i.elemento.getNome()) > 0){
            ArvoreBinaria.numComparacoes++;
            System.out.print("dir ");
            encontrado = pesquisar(nome, i.dir);
        }else{
            ArvoreBinaria.numComparacoes++;
            encontrado = true;
        }
        return encontrado;
    }
    /**
	 * Metodo para mostrar os elementos da arvore na politica "pre-fixado"
	 */
    public void mostrarPre(){
		mostrarPre(raiz); //Inicializar recursividade
	}
	private void mostrarPre(No i){
		if(i != null){
			i.elemento.imprimir();
			mostrarPre(i.esq);
			mostrarPre(i.dir);
		}
	}

}

class TP04Q01{
        static ArvoreBinaria arvore = new ArvoreBinaria();
        public static void main(String []args) throws Exception{
        MyIO.setCharset("UTF-8");

        String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            Series novaSerie = new Series();
            novaSerie.ler(entrada);
            arvore.inserir(novaSerie.clone());

            entrada = MyIO.readLine();
        }
        
        int n = MyIO.readInt();
        for(int i=0; i<n; i++){
            String entrada2[] = MyIO.readLine().split(" ");
            Series entrada3 = new Series();

            entrada3.ler(entrada2[1]);
            if(entrada2[0].charAt(0) == 'R'){
                //arvore.remover(entrada2[1]);
                //System.out.println(entrada2[1]);
            }else{
                arvore.inserir(entrada3);
            }
        }

        entrada = MyIO.readLine();
        while(!entrada.equals("FIM")){
            boolean resultadoPesquisa = arvore.pesquisar(entrada);
            if(resultadoPesquisa == true) System.out.println("SIM");
            else System.out.println("NAO");

            entrada = MyIO.readLine();
        }

        String arqLog = "625188\t" + Double.toString(ArvoreBinaria.tempoExec) + "\t" + Integer.toString(ArvoreBinaria.numComparacoes);
        Arq.openWriteClose("625188_arvoreBinaria.txt", "UTF-8", arqLog);

    }
}