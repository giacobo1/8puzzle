//import java.util.List;
//import java.util.ArrayList;
class Board{

	private int[][] board;

	public Board() {
		board= new int[3][3];
	}

	public Boolean isSolvable() {
		int i, j; //representa o ponto a ser verificado se esta invertido
		int x, y; //representa o ponto a ser comparado -- laço mais rapido
		int cont=0;

		//trecho abaixo é temporario e deve ser paralelo!
		for (i=0;i<3;i++) {
			for (j=0;j<3;j++) {
				for (x=i;x<3;x++) {
					for(y=0;y<3;y++) {
					
						if((board[x][y]!=0) && (board[i][j]!=0) && (y!=j && x!=i) && (j<=y)){
							if(board[i][j]>board[x][y]){
								cont++;
							}
						}

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

	public int[][] getBoard(int i, int j) {
		return board[i][j];
	}

}
