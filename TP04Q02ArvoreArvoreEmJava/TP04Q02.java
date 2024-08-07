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
    
        Arq.openRead(endereco,"UTF-8");

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
class NoInterno{
    Series elemento;
    NoInterno esq, dir;

    NoInterno(Series elemento){
        this(elemento, null, null);
    }

    NoInterno(Series elemento, NoInterno esq, NoInterno dir){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
    
}

class No{
    char chave;
    No esq, dir;
    NoInterno raiz;

    //Construtores
    No(char chave){
        this.chave = chave;
        this.esq = this.dir = null;
        this.raiz = null;
    }
    public No(char chave, No esq, No dir) {
		this.chave = chave;
		this.esq = esq;
		this.dir = dir;
        this.raiz = null;
	}
}

class ArvoreArvore{
    No raiz;
    static int numComparacoes = 0;
    static double tempoExec;

    //construtor
    ArvoreArvore(){
        this.raiz = null;
    }

    //Metodos

    /**
     * Insercao
     * @param s elemento (series) a ser inserido
     * @throws Exception caso o elemento ja esteja inserido na arvore
     */
    public void inserir(char chave) throws Exception{
        this.raiz = inserir( chave, raiz);//recursividade inicial
    }
    private No inserir(char chave, No i) throws Exception{
        if(i == null) i = new No(chave); // localiza o local de insercao

        else if (chave < i.chave){
            i.esq = inserir(chave, i.esq);
            numComparacoes++;
        }
        else if (chave > i.chave){
           numComparacoes++; 
           i.dir = inserir(chave, i.dir);
        }else{
            throw new Exception("ERRO AO INSERIR!!");
        }
        return i;
    }
    private No inserir(No i, Series s, char chave) throws Exception{
        if(i.chave == chave) i.raiz = inserirInterno(i.raiz, s.clone());
        else if(chave < i.chave) i.esq = inserir(i.esq, s.clone(), chave);
        else i.dir = inserir(i.dir, s.clone(), chave);
        return i;
    }
    public void inserir(Series s) throws Exception{
        this.raiz = inserir(raiz, s.clone(), s.getNome().charAt(0));
    }
    private NoInterno inserirInterno(NoInterno i, Series s) throws Exception{
        if(i == null) i = new NoInterno(s.clone());
        else{
            int comparacao = s.getNome().compareTo(i.elemento.getNome());
            if(comparacao < 0) i.esq = inserirInterno(i.esq, s.clone());
            else if(comparacao > 0) i.dir = inserirInterno(i.dir, s.clone());
            else throw new Exception("ERRO AO INSERIR!!!");
        }
        return i;
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
            encontrado = pesquisarInterno(i.raiz, nome);

            if(!encontrado){
                MyIO.print("esq ");
                encontrado = pesquisar(nome, i.esq);
            }
            if(!encontrado){
                MyIO.print("dir ");
                encontrado = pesquisar(nome, i.dir);
            }
        }
        return encontrado;
    }

    private boolean pesquisarInterno(NoInterno i, String nome){
        boolean encontrado = false;
        if(i != null){
            encontrado = nome.equals(i.elemento.getNome());
            ArvoreArvore.numComparacoes++;
            if(!encontrado){
                MyIO.print("ESQ ");
                encontrado = pesquisarInterno(i.esq, nome);
            }
            ArvoreArvore.numComparacoes++;
            if(!encontrado){
                MyIO.print("DIR ");
                encontrado = pesquisarInterno(i.dir, nome);
            }
        }
        return encontrado;
    }

}

class TP04Q02{
        static ArvoreArvore arvore = new ArvoreArvore();
        public static void main(String []args) throws Exception{
        MyIO.setCharset("UTF-8");

        String entrada = MyIO.readLine();

        char sequencia[] = { 'D', 'R', 'Z', 'X', 'V', 'B', 'F', 'P', 'U', 'I', 'G', 'E', 'J', 'L', 'H', 'T', 'A', 'W', 'S', 'O', 'M', 'N', 'K', 'C', 'Y', 'Q'};
        for(char i : sequencia) arvore.inserir(i);

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

        String arqLog = "625188\t" + Double.toString(ArvoreArvore.tempoExec) + "\t" + Integer.toString(ArvoreArvore.numComparacoes);
        Arq.openWriteClose("625188_ArvoreArvore.txt", "UTF-8", arqLog);

    }
}