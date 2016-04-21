//import java.util.List;
//import java.util.ArrayList;
import java.io.*;

/*
 *	Implementar:
 *	- getnextboard;
 *	- findwhitetale;
 *	- isInsolutionpath
 *	- isequalinposition
 *
 * */

class Board{

	private int[][] board;

	public Board(String fileName) {

		board = new int[3][3];

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName)); 
			String line = null;
			
			int i = 0;
			
			// Assumes 0 represents the blank tile;
			while ( ( line = reader.readLine() ) != null) {
				String[] data = line.split(" ");

				for (int j = 0; j < 3; j++) {
					board[i][j] = Integer.parseInt(data[j]);
				}

				i++;
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

	}


	public void printBoard() {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.format("%d ", board[i][j]);
			}
			System.out.format("\n");
		}
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


}
