class Cometa{
	public static void main(String[] args){
		int anoAtual = MyIO.readInt();
	
		MyIO.println(Cometa(anoAtual));
	}

	static int Cometa(int anoAtual){
	//o primeiro ano do cometa foi 10
	int n = ((anoAtual - 10)/76)+1;
	return (n*76+10);	
	}

}
