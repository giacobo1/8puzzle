import java.io.*;
import java.util.Vector;

class Board{

	private Board parent;
	private int[][] board;


	public Board(String fileName) {

		this.board = new int[3][3];

		this.parent = null;

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

	public Board(int[][] b) {
		
		this.board  = new int[3][3];

		this.parent = null;		

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.board[i][j] = b[i][j];
			}
		}
	}

	public void setParentNode(Board p) {
		this.parent = p;
	}

	public Board getParentNode() {
		return this.parent;
	}

	public int[][] getBoard() {
		return this.board;
	}
	
	// Testada e funcionando 
	public boolean isEqual(int[][] b) {
		boolean result = true;

		for (int i = 0; i < 3; i++)
		for (int j = 0;	j < 3; j++) {
			result = result && (this.board[i][j] == b[i][j]); 
		}

		return result;
	}

	public Pair findWhiteTile() {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.board[i][j] == 0) return new Pair(i, j); 
			}
		}

		return null;	
	}

	

	
	private Board swap(Pair original, Pair objective) {
		int value = this.board[objective.i][objective.j];
		this.board[objective.i][objective.j] = this.board[original.i][original.j];
		this.board[original.i][original.j]   = value;

		return this;	
	}	

	
	// Number of generated nodes = (nodeCount+=)result.size();
	public Vector<Board> generateNextBoard(Pair p) {
		
		Vector<Board> result = new Vector<Board>();

		int i = p.i;
		int j = p.j;

		if ( ((i + j) % 2) == 0) {
		
			if (j == 2) {
				
				if ( i == 0 ) {
					// podia refatorar o denominador comum	
					result.add((new Board(this.board)).swap(p, new Pair(i, j - 1)));		
					result.add((new Board(this.board)).swap(p, new Pair(i + 1, j)));		

				} else {
					result.add((new Board(this.board)).swap(p, new Pair(i - 1, j)));		
					result.add((new Board(this.board)).swap(p, new Pair(i, j - 1)));	
				}

			} else {
				
				if ( i == 0 ) {
					// podia refatorar o denominador comum	
					result.add((new Board(this.board)).swap(p, new Pair(i, j + 1)));		
					result.add((new Board(this.board)).swap(p, new Pair(i + 1, j)));		

				} else if (i == 2){
					result.add((new Board(this.board)).swap(p, new Pair(i - 1, j)));		
					result.add((new Board(this.board)).swap(p, new Pair(i, j + 1)));	
				
				} else {
					result.add((new Board(this.board)).swap(p, new Pair(i, j + 1)));		
					result.add((new Board(this.board)).swap(p, new Pair(i, j - 1)));
					result.add((new Board(this.board)).swap(p, new Pair(i + 1, j)));
					result.add((new Board(this.board)).swap(p, new Pair(i - 1, j)));
				}
			}	
		
		} else {
			
			if (j == 0) {
				result.add((new Board(this.board)).swap(p, new Pair(i, j + 1)));		
				result.add((new Board(this.board)).swap(p, new Pair(i - 1, j)));
				result.add((new Board(this.board)).swap(p, new Pair(i + 1, j)));

			} else if (j == 1) {// yet, 2 cases

				if (i == 0) {
					result.add((new Board(this.board)).swap(p, new Pair(i + 1, j)));		
					result.add((new Board(this.board)).swap(p, new Pair(i, j + 1)));
					result.add((new Board(this.board)).swap(p, new Pair(i, j - 1)));
				} else {
					result.add((new Board(this.board)).swap(p, new Pair(i - 1, j)));		
					result.add((new Board(this.board)).swap(p, new Pair(i, j - 1)));
					result.add((new Board(this.board)).swap(p, new Pair(i, j + 1)));
				}

			} else {
				result.add((new Board(this.board)).swap(p, new Pair(i, j - 1)));		
				result.add((new Board(this.board)).swap(p, new Pair(i + 1, j)));
				result.add((new Board(this.board)).swap(p, new Pair(i - 1, j)));

			}	
		
		}	

		
			return result;
		}
	

	public void printBoard() {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if (this.board[i][j] == 0) {
				
					System.out.format("  "); 
				} else {
					
					System.out.format("%d ", board[i][j]);
				}
			}
			System.out.format("\n");
		}
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
				if((temp[i]!=0) && (temp[j]!=0)){
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


}
