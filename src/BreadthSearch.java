import java.util.*;
import java.util.Vector;
class BreadthSearch extends Search {

	private int level;

	private ArrayList<Board> visited;

	BreadthSearch(Board b, Board s) {
		super(b,s);
		this.level = 0;
		this.visited= new ArrayList<Board>();
	
		this.run();
	}


	// lembrar de contar o numero de nos gerados
	public void BFS(Board v, Queue<Board> q) {

		this.level++;
		this.visited.add(v);
		v.printBoard();

		System.out.format("\n");
		

		if (v.isEqual(this.solutionBoard.getBoard())) {
			this.solutionBoard=v;
			System.out.println("Termino com " + this.level + " nos gerados. ");
			return;
		}

		
		for (Board b: v.generateNextBoard(v.findWhiteTile())) {
		
			//verifica se é o nodo inicial, se for nao coloca na lista
			if (!this.isVisited(b)){//!b.isEqual(this.initialBoard.getBoard())) {//!this.isVisited(b)){
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
		this.addToSolutionPath(u);

		while( u != null ) {
			u = u.getParentNode();
			this.addToSolutionPath(u);
		}

	}

	//mostra todos caminhos até a solução
	public void printSolutionPath() {
		for(int i=0;i<getSolutionCount()-1;i++){
			getSolutionPath().get(i).printBoard(); 
			System.out.println(" ");
		}

	}

	public Boolean isVisited(Board b) {
		for(int i=0;i<visited.size();i++){
			if(visited.get(i).isEqual(b.getBoard())){
				return true;
			}
		}
		return false;
	}


	public void run() {

		Queue<Board> Q = new LinkedList<Board>();
		

		BFS(this.initialBoard, Q);
		
		this.buildSolutionPath();

		this.printSolutionPath();

	}

}