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
 * Classe hash
 */
class HashIndireta{
    Series tabela[];
    int tamTab, tamReserva, qtdeReserva = 0;

    static int numComp = 0;
    static double tempoExec;

    //construtor
    HashIndireta(){
        this(21,9);
    }

    HashIndireta(int tamTab, int tamReserva){
        this.tamTab = tamTab;
		this.tamReserva = tamReserva;
		this.tabela = new Series[tamTab + tamReserva];
    }
    /**
	 * Função de Transformação
	 * @param x elemento a ser transformado
	 * @return código hash do elemento recebido (paginaTam % tamTab)
	 */
	private int hash(String x){
        byte[] bytes = x.getBytes();
        int soma = 0;
        for(int i=0; i<x.length(); i++){
            soma += bytes[i];
        }
		return (int)(soma % tamTab);
	}
    /**
	 * Método para inserir um novo elemento na tabela
	 * @param t elemento (Series) a ser inserido
	 * @return TRUE caso seja possível inserir (caso tenha espaço) o elemento; FALSE caso não.
	 */
	public boolean inserir(Series t){
		boolean inserido = true;
		int chave = hash( t.getNome() );

		if(this.tabela[chave] == null) this.tabela[chave] = t;
		else if(qtdeReserva < tamReserva) this.tabela[tamTab + qtdeReserva++] = t.clone();
		else inserido = false;

		return inserido;
	}
    
    /**
	 * Método para pesquisar a existência de um dado elemento na tabela.
	 * @param t elemento (Series) a ser pesquisado
	 * @return int referente à posição do elemento na tabela. Se o elemento não existir, retorna -1.
	 */
	public int pesquisar(Series t){
		int resp = -1, chave = hash(t.getNome());

        numComp++; //Comparação feita no if abaixo: 
        if(this.tabela[chave] == t) resp = chave;
        
		else for(chave = qtdeReserva; chave<tamReserva; chave++){
            numComp++;
			if(this.tabela[chave] == t){
                resp = chave;
                chave = tamReserva;
            }
		}

		return resp;
	}

    /**
     * Método para pesquisar a existência de um dado time na tabela pelo atributo nome.
     * @param chave String com o nome do elemento a ser procurado
     * @return int referente à posição do elemento na tabela. Se o elemento não existir, retorna -1.
     */
    public int pesquisar(String chave){
        int resp = -1;
        for(int i=0; i<(tamTab + qtdeReserva); i++){
            if(this.tabela[i]!=null){
                numComp++; //Comparação feita no if abaixo:
                if(chave.equals( this.tabela[i].getNome() )){
                    resp = i;
                    i = tamTab + qtdeReserva;
                }
            }
        }
        return resp;
    }
}

class TP04Q08{
        static HashIndireta arvore = new HashIndireta();
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
            
            if(arvore.pesquisar(entrada) >= 0) System.out.println(" SIM");
            else System.out.println(" NAO");

            entrada = MyIO.readLine();
        }

        String arqLog = "625188\t" + Double.toString(HashIndireta.tempoExec) + "\t" + Integer.toString(HashIndireta.numComp);
        Arq.openWriteClose("625188_hashIndireta.txt", "UTF-8", arqLog);

    }
}