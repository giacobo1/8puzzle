import java.util.Stack;
import java.util.Vector;

public class DepthSearch extends Search {
	
	private int level;

	DepthSearch(Board b, Board s) {
		super(b,s);

		this.level = 0;
	}


	// lembrar de contar o numero de nos gerados
	public void DFS(Board v, Stack<Board> q) {

		v.printBoard();
		System.out.format("\n");
		/*	
		if (v.isEqual(this.metaBoard.getBoard())) {
		
			System.out.format("to aqui");

			this.solutionBoard = v;

			return;
		}*/

		if (this.level < 20 ) {
			this.level++;

			for (Board b: v.generateNextBoard(v.findWhiteTile())) {
				


				b.setParentNode(v);
				q.push(b);
			}
		
			// if v is a solution, return

			Board u = q.pop();	
			DFS(u, q);
		
		} else return;		
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
	public void run() {

		Stack<Board> Q = new Stack<Board>();
		
		//Q.add(this.initialBoard.generateNextBoard(this.initialBoard.findWhiteTile()));
		
		// acho que tem que ser pra cada no
		DFS(this.initialBoard, Q);
		
		//buildSolutionPath();


	}
}
