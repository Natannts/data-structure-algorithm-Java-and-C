class Monica{
	
	public static void main (String[] args){
		int M = MyIO.readInt();
		int P = MyIO.readInt();
		int S = MyIO.readInt();

	MyIO.println(Monica(M,P,S));

	}

	static int Monica(int M, int P, int S){//M - Monica, P - primeiro filh, S - segundo filho
	int T = M - P - S;


	if( T > P && T > S) return T;
	else if (P > T && P > S) return P;
	else return S;
	}
}
