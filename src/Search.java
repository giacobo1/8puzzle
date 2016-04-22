import java.util.ArrayList;

/* class Search;
 *
 * This class represents a framework for its subclasses. It defines the search method as well as
 * stablishes the common utilities to be used by its subclasses.
 *
 * */

public class Search {

	private int nodeCounter;
	private Board initialBoard;
	private Board solutionBoard;
	private ArrayList<Board> solutionPath;

	// It needs two boards for comparision sake.
	public Search(Board b, Board s) {
	
		this.nodeCounter   = 0;
		this.initialBoard  = b;
		this.solutionBoard = s;
		this.solutionPath  = new ArrayList<Board>();
	}

	// service for graphic interface;
	public int getTotalOfNodes() {
		return nodeCounter;
	}

	// service for graphic interface;
	public int getSolutionCount() {
		return this.solutionPath.size();
	}

	// Add the new-born board to the solutionpath.	
	public void addToSolutionPath(Board b) {
		this.solutionPath.add(b);
	}
	
	// service for graphic interface;
	public ArrayList<Board> getSolutionPath() {
		return this.solutionPath;
	}



	//Utilizado no Breadth
	public Board getSolutionBoard() {
		return this.solutionBoard;
	}
	//Utilizado no Breadth
	public Board getInitialBoard() {
		return this.initialBoard;
	}
	//Utilizado no Breadth
	public void addToSolutionBoard(Board b) {
		this.solutionBoard=b;
	}


	//mostra todos caminhos até a solução
	public void printSolutionPath() {
		for(int i=0;i<solutionPath.size();i++){
			solutionPath.get(i).printBoard(); 
			System.out.println(" ");
		}
	}



	// This method implements the actual search. Therefore, it must be overriden by its subclasses;
	public void run() {}

}
