import java.util.*;
import java.util.Vector;
class BreadthSearch extends Search {

	private int level;

	private Hashtable visited;

	BreadthSearch(Board b, Board s) {
		super(b,s);
		this.level = 0;
	
		this.run();
	}


	// lembrar de contar o numero de nos gerados
	public void BFS(Board v, Queue<Board> q) {

		this.level++;
		//v.printBoard();

		//System.out.format("\n");
		

		if (v.isEqual(this.solutionBoard.getBoard())) {
			this.solutionBoard=v;
			System.out.println("Termino com " + this.level + " nos gerados.");
			return;
		}

		
		for (Board b: v.generateNextBoard(v.findWhiteTile())) {
		
			//verifica se é o nodo inicial, se for nao coloca na lista
			if (!b.isEqual(this.initialBoard.getBoard())) {
				b.setParentNode(v);
				q.add(b);
			}

		}


		Board u = q.poll();
		BFS(u, q);
			
	}

	// funcionando
	public void buildSolutionPath() {
		
		Board u = this.solutionBoard;

		while( u != null ) {
			this.addToSolutionPath(u);
			u = u.getParentNode();
		}

	}

	public void run() {

		Queue<Board> Q = new LinkedList<Board>();
		

		BFS(this.initialBoard, Q);
		
		buildSolutionPath();

	}

}