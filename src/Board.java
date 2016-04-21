class Board{

	private int[][] board;

	public Board() {
		board = new int[3][3];
	}

	public Boolean isSolvable() {
		int i, j; //representa o ponto a ser verificado se esta invertido
		int x, y; //representa o ponto a ser comparado -- laço mais rapido
		int cont=0;
		int[] temp= new int[9];

		x=0;
		for (i=0;i<3;i++) {
			for (j=0;j<3;j++) {
				temp[x]=board[i][j];
				x++;
			}
		}

		//trecho abaixo é temporario e deve ser paralelo!
		for (i=0;i<8;i++) {
			for (j=i+1;j<9;j++) {
				if((temp[i]!='0') && (temp[j]!='0')){
					if(temp[i]>temp[j]){
						cont++;
					}
				}
			}
		}
		if((cont%2)==0){
			return true;
		}else{
			return false;
		}
	}

	public void setBoard(int i, int j, int dado) {
		this.board[i][j]=dado;
	}

	public int getBoard(int i, int j) {
		return this.board[i][j];
	}

}
