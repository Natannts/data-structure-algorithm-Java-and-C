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

class Lista{
    private Series serie[];
    private int contador = 0;

        //Atributos para o arquivo log:
        public int numComparacoes = 0;
        public int numMovimentacoes = 0;

    //construtor
    Lista(){
        this(1000);
    }

     /**
     * @param x tamanho da lista
     */
    Lista(int x){
        serie = new Series[x];
    }

    //Metodos Lista
    /**
     * Insere uma Serie no inicio da lista e "arreda" os outros elementos para o fim da lista
     * @param Series serie a ser inserida
     * @throws Exception caso a lista esteja cheia
     */
    public void inserirInicio(Series series) throws Exception{
        //validar insercao
        if(contador >= serie.length) throw new Exception("ERRO AO INSERIR!!!");

        //arreda as series para o fim do array
        for(int i=contador; i>0; i--){
            serie[i] = serie[i-1].clone();
        }

        serie[0] = series.clone();//insercao
        contador++;
    }
    /**
     * Insere uma serie no fim da lista
     * @param serie serie a ser inserida
     * @throws Exception caso a lista esteja cheia
     */
    public void inserirFim(Series series) throws Exception{
        //validar insercao
        if(contador >= serie.length ) throw new Exception("ERRO AO INSERIR!!!");

        serie[contador] = series.clone();//insercao
        contador++;
    }

    /**
     * Insere uma serie no fim da lista
     * @param serie serie a ser inserida
     * @throws Exception caso a lista esteja cheia
     */
    public void inserir(Series series, int pos) throws Exception{
        //validar insercao
        if(contador >= serie.length) throw new Exception("ERRO AO INSERIR!!!");
        else if(pos<0 || pos>contador) throw new Exception("ERRO AO INSERIR!!! POSICAO NAO EXISTE!");

        //arreda as series para o fim do array
        for(int i=contador; i>pos; i--){
            serie[i] = serie[i-1].clone();
        }

        serie[pos] = series.clone();//insercao
        contador++;
    }

    /**
     * Remove a primeira serie da lista e move os demais para o inicio.
     * @return serie removida da lista
     * @throws Exception caso a lista esteja vazia
     */
    public Series removerInicio() throws Exception{
        //validar remocao
        if(contador == 0) throw new Exception("ERRO AO REMOVER!!!");

        Series removido = new Series();
        removido = serie[0].clone();
        contador--;

        //arreda as series para o inicio do array
        for(int i=0; i<contador; i++){
            serie[i] = serie[i+1].clone();
        }
        return removido;
    }

    /**
     * Remove a ultima serie da lista.
     * @return serie removida da lista
     * @throws Exception caso a lista esteja vazia
     */
    public Series removerFim() throws Exception{
        //validar remocao
        if(contador == 0) throw new Exception("ERRO AO REMOVER!!!");
        return serie[--contador].clone();
    }

     /**
     * Remove uma serie especifica da lista e puxa os demais elementos para o inicio.
     * @param pos posicao da serie a ser removida
     * @return serie removida da lista
     * @throws Exception caso a posicao seja invalida ou se a lista esteja vazia
     */
    public Series remover(int pos) throws Exception{
        //validar remocao
        if(contador == 0) throw new Exception("ERRO AO REMOVER!!!");
        else if(pos<0 || pos>=contador) throw new Exception("ERRO AO REMOVER!!!");

        Series removido = new Series();
        removido = serie[pos].clone();
        contador--;

        //arreda para o inicio
        for(int i=pos; i<contador; i++){
            serie[i] = serie[i+1].clone();
        }
        return removido;
    }

     /**
     * Procura uma determinada serie na lista
     * @param serie serie a ser pesquisada
     * @return TRUE, se a serie existir na lista; FALSE se nao.
     */
    public boolean pesquisar(Series series){
        boolean serieRegistrada = false;
        int i=0;

        while(i<contador && !serieRegistrada){
            if(serie[i] == series) serieRegistrada = true;
            else i++;
        }
        return serieRegistrada;
    }

    //Mostra recursivamente todas as series registradas(do inicio p/ o fim)
    public void mostrarRec(){
        mostrarRec(0);
    }
    public void mostrarRec(int i){
        if(i<contador){
            //System.out.print("[" + i + "] ");
            serie[i].imprimir();
            mostrarRec(i+1);
        }
    }

    //Metodos para ordenacao por selecao:
    /**
     * Registra o tepo de execucao do algoritmo de ordenacao por selecao
     * @return (double) tempo de execucao do metodo de ordenacao por selecao 
     */
    public double ordenar(){
        //Realizar ordenacao do array:
        long inicioExec = new Date().getTime();
        heapsort();
        insertionSort();
        double tempoExec = (new Date().getTime() - inicioExec) / 1000.0;

        //Retornar o tempo de execucao do algoritmo de ordenacao:
        return tempoExec;
    }

    /**
     * Algoritmo de ordenacao por heapsort
     */
    private void heapsort(){
        //Movimentar elementos da lista p/ remover a posicao 0:
        for(int i = contador; i > 0; i--) serie[i] = serie[i-1].clone();

        //Construir o heap:
        for(int tamHeap = 2; tamHeap <= contador; tamHeap++) constroi(tamHeap);

        //Realizar a ordenacao
        int tamHeap = contador;
        while(tamHeap > 1){
            swap(1, tamHeap--);
            reconstroi(tamHeap);
        }

        //Movimentar os elementos da lista para a posicao 0
        for(int i = 0; i < contador; i++){
            serie[i] = serie[i+1].clone();
        }
    }
    /**
     * Constroi o heap
     * @param tam int relativo a variavel tamHeap
     */
    private void constroi(int tam){
        while( (tam > 1) && (serie[tam].getFormato().compareTo(serie[tam/2].getFormato()) > 0)){
            swap(tam, tam/2);
            tam /= 2;
            numComparacoes++;
        }
        if(tam>1 == true) numComparacoes++;
    }
    /**
     * Realiza a re-construcao do heap
     * @param tam int relativo a variavel tamHeap
     */
    private void reconstroi(int tam){
        boolean ordenado = false;
        int filho, i = 1;
        while(!ordenado && i <= tam/2){
            //Definir filho:
            if( (serie[2*i].getFormato() .compareTo(serie[2*i+1].getFormato()) > 0) || (2*i == tam) ) filho = 2*i;
            else filho = 2*i+1;
            numComparacoes++; //Registrar operacao relevante (feita no if)

            //Reconstruir o heap enquanto nao estiver ordenado:
            if(serie[i].getFormato() .compareTo(serie[filho].getFormato()) < 0){
                swap(i, filho);
                i = filho; //Atualizar variavel de controle "i"
            }
            else ordenado = true;
            numComparacoes++; //Registrar operacao relevante (feita no if)
        }
    }
    /**
     * Realiza a troca ("swap") de dois elementos da lista
     * @param i indice de um das serie a ser trocada de posicao
     * @param j indice de outra serie a ser trocada de posicao
     */
    private void swap(int i, int j){
        Series tmp = serie[i].clone();
        serie[i] = serie[j].clone();
        serie[j] = tmp.clone();

        numMovimentacoes += 3; //Registrar movimentacoes feitas (+3)
    }

    /**
	 * Algoritmo de ordenacao por insercao.
	 */
   public void insertionSort() {
		for (int i = 1; i < contador; i++) {
			Series tmp = serie[i];
         int j = i - 1;

         while ((j >= 0) && (serie[j].getFormato().compareTo(tmp.getFormato()) == 0)
                && serie[j].getNome().compareTo(tmp.getNome()) > 0) {
            serie[j + 1] = serie[j];
            j--;
         }
         serie[j + 1] = tmp;
      }
	}

}

class heapsort{

    public static void main(String []args) throws Exception{
    
        MyIO.setCharset("UTF-8");
        Lista serie = new Lista(200);

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
       double tempoOrdenacao = serie.ordenar();
       serie.mostrarRec();

       //Arquivo log
       String arqLog = "625188\t" + Integer.toString(serie.numComparacoes) 
                        + "\t" + Integer.toString(serie.numMovimentacoes)
                        + "\t" + Double.toString(tempoOrdenacao);
        Arq.openWriteClose("matrícula_heapsort.txt","UTF-8", arqLog);

        
    }
}