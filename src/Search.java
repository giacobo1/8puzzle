import java.util.ArrayList;

/* class Search;
 *
 * This class represents a framework for its subclasses. It defines the search method as well as
 * stablishes the common utilities to be used by its subclasses.
 *
 * */

public class Search {

	protected int nodeCounter;
	protected Board initialBoard;
	protected Board solutionBoard;
	protected ArrayList<Board> solutionPath;

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

	// This method implements the actual search. Therefore, it must be overriden by its subclasses;
	public void run() {}

}
