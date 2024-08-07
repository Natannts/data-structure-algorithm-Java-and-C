import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Date;

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
					setFormato(aux.trim());
                    
				}
			 	else if(aux.equals("Duração") && linha < 200){
                    vetor[linha] = Arq.readLine();
                    aux = vetor[linha].replaceAll("<.*?>" ,"");
                    setDuracao(aux.trim());
                    
                }
                else if(aux.equals("País de origem"))
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
                    aux = aux.replaceAll("&#160;", "");
					aux = aux.replaceAll("&nbsp;", "");
					setPaisOrigem(aux.trim());
                    
                }
                else if(aux.equals("Idioma original"))//olhar espaços antes do idioma
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
					setIdioma(aux.trim());
                   
				}
                else if(aux.equals("Emissora de televisão original"))//olhar espaços antes
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
					setEmissoraOriginal(aux.trim());
                    
				}
                else if(aux.equals("Transmissão original"))
				{
					vetor[linha] = Arq.readLine();
					aux = vetor[linha].replaceAll("<.*?>" ,"");
                    aux = aux.replaceAll("&#160;", "");
					aux = aux.replaceAll("&nbsp;", "");
					setTransmicaoOriginal(aux.trim());
					
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

//Classe CELULA -------------------------------------------------------
/**
 * Celula - Elemento de TADs Flexiveis(Lista, Pilha e Fila)
 * @Description Celula com 1 ponteiro
 */
class Celula{
    public Series elemento;//Elemento contido na celula
    public Celula prox;//Aponta para proxima celula

    /**
     * Construtor padrao da classe
     */
    public Celula(){
        this(null);
    }
    /**
     * Construtor da classe
     * @param elemento elemento (serie) a ser inserida na celula
     */
    public Celula(Series elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}
//Lista Flexivel -------------------------------------------------------
/**
 * TAD: Lista Flexivel / Dinamica
 */
class Fila{
    private Celula primeiro;
    private Celula ultimo;

    /**
     * Construtor da classe
     */
    public Fila(){
        primeiro = ultimo = new Celula();
    }

    //Operadores e metodos da fila---------------------------
    /**
     * Calcula o tamanho atual da fila
     * @return int referente ao tamanho da fila - em numero de elementos
     */
    public int tamanho(){
        int tam = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
    }
    /**
     * Insere novo elemento em uma dada posicao da lista (considerando o 1.o elemento valido a partir da posicao 0)
     * @param serie serie a ser inserida
     */
    public void inserir(Series series) throws Exception{
        if(tamanho() == 5){
            remover();
        }
        ultimo.prox = new Celula(series);
        ultimo = ultimo.prox;
        ultimo.prox = primeiro;
    }
    /**
     * Remove o elemento de uma dada posicao da lista
     * @param pos posicao da qual pretende-se realizar a remocao
     * @return elemento (serie) removido
     * @throws Exception caso a lista esteja vazia OU caso a posicao seja invalida
     */
    public Series remover() throws Exception{
        if (primeiro == ultimo) throw new Exception("ERRO!");

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Series elemento = primeiro.elemento;
        ultimo.prox = primeiro;
        tmp.prox = null;
        tmp = null;
        return elemento;
    }
    /**
     * Mostra todos os elementos (series) registrados na fila
     */
    public void mostrar(){
        for(Celula i=primeiro.prox; i!=primeiro; i=i.prox){
            i.elemento.imprimir();
        }
    }


    public int somar(){
        int resp=0;
        for(Celula i = primeiro.prox; i != primeiro; i = i.prox){
            resp += i.elemento.getQntTemporadas();
        }
        return resp;
    }

    public int arredondar(double x){
        int resp = 0;
        int aux = (int)x;
        double frac = x - aux;
        frac = frac*10;
        if((int)frac >= 5 ){
            resp = (int)x + 1;
        }else{
            resp = (int)x;
        }
        return resp;
    }

    public int calcularMedia(){
        double resp = 0;
        double n = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox, n++);
        resp = somar()/n;
        return arredondar(resp);
    }

}

class FilaCirc{

    public static void main(String []args) throws Exception{
    
        MyIO.setCharset("UTF-8");
        //Iniciar fila vazia
        Fila serie = new Fila();

        //Entrada padrao--------
        String arq = MyIO.readLine();
        while(!arq.equals("FIM")){
            Series series = new Series();//Instanciar nova serie
            
            //registrar atributo
            series.ler(arq);
            
            //registrar serie
            serie.inserir(series);

            //Proxima leitura
            arq = MyIO.readLine();

            System.out.println(serie.calcularMedia());
        }
        
        //Entrada secundaria-------
        int n = MyIO.readInt();//Quantidade de insercoes/remocoes

        for(int i=0; i<n; i++){
            String entrada[] = MyIO.readLine().split(" ");
 
            //Leitura do comando de iserir
            if(entrada[0].charAt(0) == 'I'){
                Series novo = new Series();
                novo.ler(entrada[1]);
                serie.inserir(novo);
                System.out.println(serie.calcularMedia());
            }
            //Leitura comando insercao
            else{
                serie.remover();
            }
        
        }

    }
}