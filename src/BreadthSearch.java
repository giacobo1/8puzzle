import java.util.*;
import java.util.Vector;
class BreadthSearch extends Search {

	private int level;

	BreadthSearch(Board b, Board s) {
		super(b,s);
		this.level = 0;
	
		this.run();
	}


	// lembrar de contar o numero de nos gerados
	public void BFS(Board v, Queue<Board> q) {

		//this.level++;
		v.printBoard();

		System.out.format("\n");
		
		if (v.isEqual(this.getInitialBoard().getBoard())) {
			this.level++;
		}

		if (v.isEqual(this.getSolutionBoard().getBoard())) {
			this.addToSolutionBoard(v);
			System.out.println("Termino com nivel: " + this.level);
			return;
		}

		//if (v.isEqual(v.generateNextBoard(v.findWhiteTile()).getBoard())) {
		//	this.level++;
		//}

		for (Board b: v.generateNextBoard(v.findWhiteTile())) {
			
			b.setParentNode(v);
			q.add(b);
		}


		Board u = q.poll();
		BFS(u, q);
			
	}

	// funcionando
	public void buildSolutionPath() {
		
		Board u = getSolutionBoard();

		while( u != null ) {
			this.addToSolutionPath(u);
			u = u.getParentNode();
		}

	}

	public void run() {

		Queue<Board> Q = new LinkedList<Board>();
		

		BFS(this.getInitialBoard(), Q);
		
		buildSolutionPath();


	}

}