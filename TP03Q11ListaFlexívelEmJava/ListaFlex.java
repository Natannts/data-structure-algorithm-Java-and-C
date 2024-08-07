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
class Lista{
    private Celula primeiro;
    private Celula ultimo;

    /**
     * Construtor da classe
     */
    public Lista(){
        primeiro = ultimo = new Celula();
    }

    //Operadores e metodos da lista---------------------------
    /**
     * Calcula o tamanho atual da lista
     * @return int referente ao tamanho da lista - em numero de elementos
     */
    public int tamanho(){
        int tam = 0;
        for(Celula i = primeiro.prox; i != null; i = i.prox) tam++;
        return tam;
    }
    /**
     * Insere uma Serie no inicio da lista e "arreda" os outros elementos para o fim da lista
     * @param Series serie a ser inserida
     */
    public void inserirInicio(Series series) throws Exception{
        //Criar celula temporaria com o elemento que pretende-se inserir
        Celula tmp = new Celula(series.clone());

        //Conectar (inserir) celula temporaria na lista:
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        //Se a lista estava vazia, definir ultimo como a celula inserida
        if(primeiro == ultimo) ultimo = tmp;

        //Limpar memoria da celula temporaria
        tmp = null;
    }
    /**
     * Insere uma serie no fim da lista
     * @param serie serie a ser inserida
     */
    public void inserirFim(Series series) throws Exception{
        ultimo.prox = new Celula(series.clone()); //Instanciar celula a ser inserida na lista
        ultimo = ultimo.prox; //Atualizar atributo "ultimo"
    }

    /**
     * Insere novo elemento em uma dada posicao da lista (considerando o 1.o elemento valido a partir da posicao 0)
     * @param serie serie a ser inserida
     */
    public void inserir(Series series, int pos) throws Exception{
        
        int tam = tamanho();
        
        //Validar insercao e tratar excecoes de quando "pos==0" ou "pos==tamanho()":
        if((pos < 0) || (pos > tam)) throw new Exception("Erro ao inserir! Posicao invalida!");
        else if(pos == 0) inserirInicio(series); //Caso a posicao seja 0, chamar metodo "inserirInicio()"
        else if(pos == tam) inserirFim(series); //Caso a posicao seja a ultima da lista, chamar metodo "inserirFim()"

        else{
            //Caminhar ate encontrar a posicao anterior a qual pretende-se realizar a insercao":
            Celula i = primeiro;
            for(int j=0; j<pos; j++) i = i.prox;

            //Realizar insercao:
            Celula tmp = new Celula(series.clone());
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null; //Liberar memorias das celulas "i" e "tmp"
        }
    }

    /**
     * Remove o primeiro elemento da lista
     * @return elemento (serie) removido
     * @throws Exception caso a lista esteja vazia
     */
    public Series removerInicio() throws Exception{
        //Validar remocao:
        if(primeiro == ultimo) throw new Exception("Erro ao remover! Lista vazia!");

        //Realizar remocao:
        Series removido = primeiro.prox.elemento.clone();
        Celula tmp = primeiro;
        primeiro = primeiro.prox;

        //Liberar memoria antes ocupada por "primeiro":
        tmp.prox = null;
        tmp = null;

        return removido.clone();
    }

    /**
     * Remove o ultimo elemento da lista
     * @return elemento (serie) removido
     * @throws Exception caso a lista esteja vazia
     */
    public Series removerFim() throws Exception{
        //Validar remocao:
        if(primeiro == ultimo) throw new Exception("Erro ao remover! Lista vazia!");

        //Realizar remocao:
        Celula i = primeiro;
        while(i.prox != ultimo) i = i.prox; //Caminhar ate a penultima posicao da lista

        Series removido = ultimo.elemento.clone();
        ultimo = i;
        i = ultimo.prox = null; //Liberar memorias

        return removido.clone();
    }

    /**
     * Remove o elemento de uma dada posicao da lista
     * @param pos posicao da qual pretende-se realizar a remocao
     * @return elemento (serie) removido
     * @throws Exception caso a lista esteja vazia OU caso a posicao seja invalida
     */
    public Series remover(int pos) throws Exception{
        Series removido;
        int tam = tamanho();
        //Validar remocao e tratar excecoes de quando "pos==0" ou "pos==tamanho()":
        if(primeiro == ultimo) throw new Exception("Erro ao remover! Lista vazia!");
        else if((pos < 0) || (pos >= tam)) throw new Exception("Erro ao remover! Posicao invalida!");
        else if(pos == 0) removido = removerInicio(); //Se pos==0, chamar metodo "removerInicio()"
        else if(pos == tam-1) removido = removerFim(); //Se pos==tamanho()-1, chamar metodo "removerFim()"

        else{
            //Caminhar ate a posicao anterior a qual pretende-se realizar a remocao:
            Celula i = primeiro;
            for(int j = 0; j < pos; j++) i = i.prox;

            //Realizar remocao:
            Celula tmp = i.prox;
            removido = tmp.elemento.clone();
            i.prox = tmp.prox;

            //Liberar memorias:
            tmp.prox = null;
            tmp = i = null;
        }

        return removido.clone();
    }
    /**
     * Mostra todos os elementos (series) registrados na lista
     */
    public void mostrar(){
        for(Celula i=primeiro.prox; i!=null; i=i.prox){
            i.elemento.imprimir();
        }
    }


    /**
     * Verifica se um dado elemento (serie) esta registrado na lista (pesquisa feita atraves do atributo NOME)
     * @param nome atributo de pesquisa usado p/ pesquisar a serie na lista
     * @return TRUE caso a serie esteja registrada; FALSE caso nao.
     */
    public boolean pesquisar(String nome){
        Celula i = primeiro.prox;
        boolean encontrado = false;
        
        while(i!=null && !encontrado){
            if(i.elemento.getNome().equals( nome )) encontrado = true;
            else i = i.prox;
        }

        return encontrado;
    }
}

class ListaFlex{

    public static void main(String []args) throws Exception{
    
        MyIO.setCharset("UTF-8");
        //Iniciar lista vazia
        Lista serie = new Lista();

        //Entrada padrao--------
        String arq = MyIO.readLine();
        while(!arq.equals("FIM")){
            Series series = new Series();//Instanciar nova serie
            
            //registrar atributo
            series.ler(arq);
            
            //registrar serie
            serie.inserirFim(series);

            //Proxima leitura
            arq = MyIO.readLine();
        }
        
        //Entrada secundaria-------
        int n = MyIO.readInt();//Quantidade de insercoes/remocoes

        for(int i=0; i<n; i++){
            String entrada[] = MyIO.readLine().split(" ");

            //Leitura do comando de remocao
            if(entrada[0].charAt(0) == 'R'){
                Series removido = new Series();
                switch(entrada[0].charAt(1)){
                    case 'I': removido = serie.removerInicio(); break;
                    case 'F': removido = serie.removerFim(); break;
                    case '*': removido = serie.remover(Integer.parseInt(entrada[1])); break;
                }
                MyIO.println("(R) " + removido.getNome());
            }

            //Leitura comando insercao
            else{
                Series novo = new Series();
                switch(entrada[0].charAt(1)){
                    case '*':
                        novo.ler(entrada[2]);
                        serie.inserir(novo, Integer.parseInt(entrada[1]));
                    break;
                    case 'I':
                        novo.ler(entrada[1]);
                        serie.inserirInicio(novo);
                    break;
                    case 'F':
                        novo.ler(entrada[1]);
                        serie.inserirFim(novo);
                    break;
                }
            }
        }
        //Imprimir series
        serie.mostrar();
    }
}