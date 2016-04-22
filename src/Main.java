import java.io.*;
import java.util.*;

/*
 *	TODO:
 *		1. Comitar mudanças no board
 *		2. Adicionar contagem de nos que foram gerados no depthsearch
 *		3. Muitos testes
 *		4. Finalizar e corrigir depthsearch
 *		5. Testar e implementar geracao do solution path
 *		6. Implementar interface grafica
 * */



class Main {
	public static void main(String[] args) {	
	

		int[][] defaultBoard1 = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
		int[][] defaultBoard2 = {{0, 1, 2}, {3, 4, 5}, {6, 17, 8}};
		//Board board = new Board(args[0]);
		
		Board meta1  = new Board(defaultBoard1);
		Board meta2  = new Board(defaultBoard2);
		
		System.out.println(meta1.isEqual(meta2.getBoard()));


		//DepthSearch search = new DepthSearch(board, meta);

		//search.run();

		
		
		/*		
		Vector<Board> boardList = board.generateNextBoard(board.findWhiteTile());

		for (Board b: boardList) {
			b.printBoard();
			System.out.format("\n");
		}*/

	}
}
