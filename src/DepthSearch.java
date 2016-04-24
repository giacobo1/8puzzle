import java.util.Stack;
import java.util.Vector;
import java.util.ArrayList;

public class DepthSearch extends Search {
	
	private int level;
	private ArrayList<Board> visited;

	DepthSearch(Board b, Board s) {
		super(b,s);

		this.visited = new ArrayList<Board>();

		this.level = 0;
	}


	// lembrar de contar o numero de nos gerados
	public void DFS(Board v, int n) {

		/*
		System.out.format("Level is %d: \n", this.level );
		v.printBoard();
		System.out.format("\n");
		*/	

		if (v.isEqual(this.metaBoard.getBoard())) {
		
		//	System.out.format("to aqui\n");

			this.solutionBoard = v;

			this.solutionPath.add(v);

			return;
		}

		if (n < 40) {
			//this.level++;

			n++;

			for (Board b: v.generateNextBoard(v.findWhiteTile())) {
				
				if (!isVisited(b)) {
					
					this.nodeCounter++;
					this.visited.add(b);	
					b.setParentNode(v);
					DFS(b, n);
				}
			}			
			// if v is a solution, return

			//Board u = q.pop();	
			//DFS(u, q);
		
		} else {
		       //this.level = 0;	
			return;
		
		}

		
		return;

	}

	private boolean isVisited(Board b) {
		
		boolean result = false;

		for (Board v: this.visited) {
			if (v.isEqual(b.getBoard())) return true;
		}

		

		return result;
	}

	// precisa de testes
	public void buildSolutionPath() {
		
		Board u = solutionBoard.getParentNode();


		while( u != null ) {
			this.solutionPath.add(u);
			u = u.getParentNode();
		}

	}

	// precisa de testes
	@Override
	public void run() {
		
		//Vector<Board> firstLevel = this.initialBoard.generateNextBoard(this.initialBoard.findWhiteTile());
		// acho que tem que ser pra cada no
		/*
		for (Board b: firstLevel) {
		
			if (!DFS(b)) break;

			this.level = 0;
		}*/

		DFS(this.initialBoard, 0);

		System.out.format("Numero de nos criados: %d\n", this.nodeCounter);

		buildSolutionPath();

		System.out.format("Numero de nos na solução: %d\n\n", this.solutionPath.size());
		for (Board b: this.solutionPath) {
			b.printBoard();
			System.out.format("\n");
		}


	}
}
