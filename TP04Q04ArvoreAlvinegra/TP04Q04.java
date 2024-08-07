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
    String elemento;
    boolean cor;
    No esq, dir;

    //Construtores
    No(){
        this(null);
    }

    No(String elemento){
        this(elemento, false, null, null);
    }

    No(String elemento, boolean cor){
        this(elemento, cor, null, null);
    }

    No(String elemento, boolean cor, No esq, No dir){
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreAlvinegra{
    No raiz;
    static int comparacoes = 0;
    static double tempoExec;

    //construtor
    ArvoreAlvinegra(){
        this.raiz = null;
    }

    //Metodos

    /**
     * Insercao
     * @param s elemento (series) a ser inserido
     * @throws Exception caso o elemento ja esteja inserido na arvore
     */
    public void inserir(Series s) throws Exception{
        String elemento = s.getNome();
        if(raiz == null) raiz = new No(elemento);

        else if(raiz.esq == null  &&  raiz.dir == null){ //Se tiver apenas um elemento
            int comp = elemento.compareTo(raiz.elemento);

            ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
            if(comp < 0) raiz.esq = new No(elemento, true);
            else{
                ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                raiz.dir = new No(elemento, true);
                //else throw new Exception("Erro ao inserir! Elemento já registrado!");
            }
        }

        else if(raiz.esq == null){ //Se tiver 2 elementos (raiz e raiz.dir)
            ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
            if(elemento.compareTo(raiz.elemento) < 0) raiz.esq = new No(elemento);
            else{
                int comp = elemento.compareTo(raiz.dir.elemento);

                ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                if(comp < 0){
                    raiz.esq = new No(raiz.elemento);
                    raiz.elemento = elemento;
                }
                else{
                    ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                    
                        raiz.esq = new No(raiz.elemento);
                        raiz.elemento = raiz.dir.elemento;
                        raiz.dir.elemento = elemento;
                    }
                }
                raiz.esq.cor = raiz.dir.cor = false;
            }
        

        else if(raiz.dir == null){ //Se tiver 2 elementos (raiz e raiz.esq)
            ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
            if(elemento.compareTo(raiz.elemento) > 0) raiz.dir = new No(elemento);
            else{
                int comp = elemento.compareTo(raiz.esq.elemento);

                ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                if(comp > 0){
                    raiz.dir = new No(raiz.elemento);
                    raiz.elemento = elemento;
                }
                else{
                    ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                    
                        raiz.dir = new No(raiz.elemento);
                        raiz.elemento = raiz.esq.elemento;
                        raiz.esq.elemento = elemento;
                    
                    //else throw new Exception("Erro ao inserir! Elemento já registrado!");
                }
                raiz.esq.cor = raiz.dir.cor = false;
            }
        }

        else inserir(elemento, null, null, null, raiz); //Se tiver mais de 2 elementos

        raiz.cor = false;
    }

    /**
     * Método recursivo para inserir um dado elemento (atributo 'nome') na árvore.
     * @param elemento elemento a ser inserido
     * @param bisavo Nó em análise
     * @param avo Nó em análise
     * @param pai Nó em análise
     * @param i Nó em análise
     * @throws Exception caso o elemento já exista na árvore.
     */
    private void inserir(String elemento, No bisavo, No avo, No pai, No i) throws Exception{
        if(i == null){
            int comp = elemento.compareTo(pai.elemento);

            ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
            if(comp < 0) i = pai.esq = new No(elemento, true);
            else{
                ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                if(comp > 0) i = pai.dir = new No(elemento, true);
                //else throw new Exception("Erro ao inserir! Elemento já registrado!");
            }

            if(pai.cor) balancear(bisavo, avo, pai, i);
        }
        else{
            //Se for um 4-nó, fragmentá-lo:
            if((i.esq != null && i.esq.cor) && (i.dir != null && i.dir.cor)){
                i.esq.cor = i.dir.cor = false;
                if(i == raiz) i.cor = false;
                else if(pai.cor) balancear(bisavo, avo, pai, i);
                else i.cor = true;
            }

            int comp = elemento.compareTo(i.elemento);
            ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
            if(comp < 0) inserir(elemento, avo, pai, i, i.esq);
            else{
                ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                inserir(elemento, avo, pai, i, i.dir);
                //else throw new Exception("Erro ao inserir! Elemento já registrado!");
            }
        }
    }

    /**
     * Método para balancear a árvore em um dado vértice (nó).
     * @param bisavo Nó em análise
     * @param avo Nó em análise
     * @param pai Nó em análise
     * @param i Nó em análise
     */
    private void balancear(No bisavo, No avo, No pai, No i){
        //Se o pai também é preto, balancear a árvore (rotacionando o avô conforme necessário):
        if(pai.cor){
            ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
            if(pai.elemento.compareTo(avo.elemento) > 0){ //Rotação à esquerda (simples ou dupla)
                ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                if(pai.elemento.compareTo(i.elemento) < 0) avo = rotacionarEsq(avo);
                else avo = rotacionarDirEsq(avo);
            }

            else{ //Rotação à direita (simples ou dupla)
                ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                if(pai.elemento.compareTo(i.elemento) > 0) avo = rotacionarDir(avo);
                else avo = rotacionarEsqDir(avo);
            }

            if(bisavo != null){
                ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
                if(bisavo.elemento.compareTo(avo.elemento) > 0) bisavo.esq = avo;
                else bisavo.dir = avo;
            }
            else raiz = avo;

            //Atualizar cores após a rotação:
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    /**
     * Métodos de rotações a partir de um dado vértice (nó); usados no balanceamento da árvore:
     * @param no Nó em análise
     * @return vértice recebido por parâmetro; porém balanceado
     */
    private No rotacionarEsq(No no){ //Rotação simples à esquerda
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        no.dir = noDirEsq;
        noDir.esq = no;
        return noDir;
    }
    private No rotacionarDir(No no){ //Rotação simples à direita
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        no.esq = noEsqDir;
        noEsq.dir = no;
        return noEsq;
    }
    private No rotacionarDirEsq(No no){ //Rotação dupla à esquerda
        no.dir = rotacionarDir(no.dir);
        return rotacionarEsq(no);
    }
    private No rotacionarEsqDir(No no){ //Rotação dupla à direita
        no.esq = rotacionarEsq(no.esq);
        return rotacionarDir(no);
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
        boolean encontrado = false;

        if(i != null){
        int comp = nome.compareTo(i.elemento);
        ArvoreAlvinegra.comparacoes++;
        if(comp == 0)encontrado = true;
        else{
            ArvoreAlvinegra.comparacoes++; //Feita no if abaixo:
            if(comp < 0){
                MyIO.print("esq ");
                encontrado = pesquisar(nome, i.esq);
            }
            else{
                MyIO.print("dir ");
                encontrado = pesquisar(nome, i.dir);
            }
        }
    }
        return encontrado;
    }
    

}

class TP04Q04{
        static ArvoreAlvinegra arvore = new ArvoreAlvinegra();
        public static void main(String []args) throws Exception{
        MyIO.setCharset("UTF-8");

        String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            Series novaSerie = new Series();
            novaSerie.ler(entrada);
            arvore.inserir(novaSerie.clone());

            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();
        while(!entrada.equals("FIM")){
            boolean resultadoPesquisa = arvore.pesquisar(entrada);
            if(resultadoPesquisa == true) System.out.println("SIM");
            else System.out.println("NAO");

            entrada = MyIO.readLine();
        }

        String arqLog = "625188\t" + Double.toString(ArvoreAlvinegra.tempoExec) + "\t" + Integer.toString(ArvoreAlvinegra.comparacoes);
        Arq.openWriteClose("625188_alvinegra.txt", "UTF-8", arqLog);  

    }

    static boolean fim(String str){
		return str.length()>=3 && str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M';
	}
}