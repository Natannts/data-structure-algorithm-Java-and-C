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
 * Celula dupla - Elemento de TADs Flexiveis(Lista, Pilha e Fila)
 * @Description Celula com 1 ponteiro
 */
class CelulaDupla{
    public Series elemento;//Elemento contido na celula
    public CelulaDupla prox, ant;//Aponta para proxima celula

    /**
     * Construtor padrao da classe
     */
    public CelulaDupla(){
        this.prox = this.ant = null;
        this.elemento = new Series();
    }
    /**
     * Construtor da classe
     * @param elemento elemento (serie) a ser inserida na celula
     */
    public CelulaDupla(Series elemento){
        this.elemento = elemento;
        this.prox = this.ant = null;
    }
}

//Lista Flexivel duplamente encadeada -------------------------------------------------------
/**
 * TAD: Lista Flexivel / Dinamica
 */
class ListaDupla{
    private CelulaDupla primeiro, ultimo;
    private Series serie[];

        //Atributos para o arquivo log:
        public int numComparacoes = 0;
        public int numMovimentacoes = 0;

    //construtor
    ListaDupla(){
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    //Metodos Lista
     /**
     * Calcula o tamanho atual da lista
     * @return int referente ao tamanho da lista - em numero de elementos
     */
    public int tamanho(){
        int tam = 0;
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox) tam++;
        return tam;
    }
    /**
     * Define o tamanho de uma dada sub-lista (em numero de elementos)
     * @param limiteEsq limite esquerdo da sub-lista
     * @param limiteDir limite direito da sub-lista
     * @return inteiro relativo ao tamanho da sub-lista em questao
     */
    private int tamanho(CelulaDupla limiteEsq, CelulaDupla limiteDir){
        int tam = 0;
        while(limiteEsq != limiteDir.prox){
            tam++;
            limiteEsq = limiteEsq.prox;
        }
        return tam;
    }
     /**
     * Insere uma Serie no inicio da lista e "arreda" os outros elementos para o fim da lista
     * @param Series serie a ser inserida
     */
    public void inserirInicio(Series series){
        //Criar celula temporaria com o elemento que pretende-se inserir
        CelulaDupla tmp = new CelulaDupla(series);

        //Conectar (inserir) celula temporaria na lista:
        tmp.prox = primeiro.prox;
        primeiro = primeiro.ant = tmp;

        //Limpar memoria da celula temporaria
        tmp = null;
    }

    /**
     * Insere uma serie no fim da lista
     * @param serie serie a ser inserida
     */
    public void inserirFim(Series series){
        ultimo.prox = new CelulaDupla(series); //Instanciar celula a ser inserida na lista
        ultimo.prox.ant = ultimo;
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
            CelulaDupla i = primeiro;
            for(int j=0; j<pos; j++) i = i.prox;

            //Realizar insercao:
            CelulaDupla tmp = new CelulaDupla(series);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp.ant = i;
            //Desconectar celulas da lista e liberar memoria
            tmp.prox.ant = tmp.ant.prox = tmp;
            i.prox.ant = i.ant.prox = i;
            tmp = i = null;
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
        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        Series removido = tmp.elemento.clone();
        primeiro.ant = tmp = null;

        return removido;
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
        Series removido = ultimo.elemento.clone();
        ultimo = ultimo.ant;

        return removido;
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
            CelulaDupla i = primeiro;
            for(int j = 0; j < pos; j++) i = i.prox;

            //Realizar remocao:
            CelulaDupla tmp = i.prox;
            removido = tmp.elemento.clone();
            i.prox = tmp.prox;
            i.ant.prox = i.prox.ant = i;
            i = tmp = null;
        }

        return removido;
    }


    //Mostra recursivamente todas as series registradas(do inicio p/ o fim)
    public void mostrar(){
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox) i.elemento.imprimir();
    }
    

    //Metodos para ordenacao por selecao:
    /**
     * Registra o tepo de execucao do algoritmo de ordenacao por selecao
     * @return (double) tempo de execucao do metodo de ordenacao por selecao 
     */
    public void ordenar(){
        
        //Realizar ordenacao do array:
        quicksort();

        
    }

    /**
     * Define o pivo de uma dada lista/sub-lista
     * @param limiteEsq limite esquerdo da lista
     * @param limiteDir limite direito da lista
     * @return String pivo da lista/sub-lista em questao
     */
    private CelulaDupla definirPivo(CelulaDupla limiteEsq, CelulaDupla limiteDir){
        CelulaDupla celulaPivo = limiteEsq;
        for(int i = 0; i < (tamanho(limiteEsq, limiteDir) / 2); i++) celulaPivo = celulaPivo.prox;

        return celulaPivo;
    }
    /**
     * Define se a celula do 1.o parametro encontra-se numa posicao DEPOIS da celula do 2.o parametro
     * @param i celula de referencia do 1.o parametro
     * @param j celula de referencia do 2.o parametro
     * @return TRUE se i estiver depois de j; FALSE se nao.
     */
    private boolean maiorQue(CelulaDupla i, CelulaDupla j){
        boolean maiorQue = true;

        while(i != null && maiorQue){
            if(i == j) maiorQue = false;
            else i = i.prox;
        }
        
        return maiorQue;
    }


    /**
     * Algoritmo de ordenacao por quicksort
     */

    public void quicksort(CelulaDupla esq, CelulaDupla dir, CelulaDupla celula) {
        CelulaDupla i=esq, j=dir;
        String pivo = celula.elemento.getPaisOrigem();
        String pivoNome = celula.elemento.getNome();
        
        while(!maiorQue(i, j)){
            
            while(i.elemento.getPaisOrigem().compareTo(pivo)<0
            || i.elemento.getPaisOrigem().compareTo(pivo) == 0  && i.elemento.getNome().compareTo(pivoNome)<0){//|| i.elemento.getPaisOrigem().compareTo(pivo) == 0  && i.elemento.getNome().compareTo(pivoNome)<0
                        i = i.prox;
                        numComparacoes++;
                    }

            while(j.elemento.getPaisOrigem().compareTo(pivo)>0
            ||j.elemento.getPaisOrigem().compareTo(pivo) == 0 && j.elemento.getNome().compareTo(pivoNome)>0){//||j.elemento.getPaisOrigem().compareTo(pivo) == 0 && j.elemento.getNome().compareTo(pivoNome)>0
                        j = j.ant;
                        numComparacoes++;
                    }

            if(!maiorQue(i, j)){
                //swap
                Series tmp = i.elemento.clone();
                i.elemento = j.elemento.clone();
                j.elemento = tmp.clone();
                numMovimentacoes+=3;

                //movimentar celulas i e j
                i = i.prox;
                j = j.ant;
            }
        }
        if(maiorQue(j, esq)) quicksort(esq, j, definirPivo(esq, j));
        if(maiorQue(dir, i)) quicksort(i, dir, definirPivo(i, dir));
	}

    private void quicksort(){
        quicksort(primeiro, ultimo, definirPivo(primeiro, ultimo));//Inicializar recursividade p/ o quicksort
    }
}

class quicksort2{

    public static void main(String []args) throws Exception{
    
        MyIO.setCharset("UTF-8");
        ListaDupla serie = new ListaDupla();

        //Entrada padrao
        String arq = MyIO.readLine();
        while(!arq.equals("FIM")){
            Series series = new Series();//Instanciar nova serie
            
            //registrar serie
            series.ler(arq);
            serie.inserirFim(series);

            //Proxima leitura
            arq = MyIO.readLine();
        }

       //Ordenação
       long inicioExec = new Date().getTime();
       serie.ordenar();
       double tempoOrdenacao = (new Date().getTime() - inicioExec) / 1000.0;
       serie.mostrar();

       //Arquivo log
       String arqLog = "625188\t" + Integer.toString(serie.numComparacoes) 
                        + "\t" + Integer.toString(serie.numMovimentacoes)
                        + "\t" + Double.toString(tempoOrdenacao);
        Arq.openWriteClose("matrícula_quicksort2.txt","UTF-8", arqLog);

        
    }
}