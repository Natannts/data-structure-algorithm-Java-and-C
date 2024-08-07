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

class ClasseSeries{

    public static void main(String []args) throws Exception{
    
        String[] arq = new String[1000];
		String linha;
		int quantEntrada = 0;
        Series series = null;
        
        do
		{
			arq[quantEntrada] = MyIO.readLine();
		}
		while(arq[quantEntrada++].equals("FIM") != true );
        quantEntrada--;
        
        int i =0;
        while(i<quantEntrada){
            series = new Series();
            series.ler(arq[i]);
            series.imprimir();
            i++;
        }


    }
}