import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) {	
	
		Board board = new Board(args[0]);
		Board metaboard = new Board(args[1]);

		//board.printBoard();

			
		/*Vector<Board> boardList = board.generateNextBoard(board.findWhiteTile());

		for (Board b: boardList) {
			b.printBoard();
			System.out.format("\n");
		}*/

		//board.printBoard();

//		metaboard.printBoard();

		if (board.isSolvable()) {
			System.out.println("Play.\n");
			BreadthSearch breadth = new BreadthSearch(board, metaboard);
			breadth.printSolutionPath();
		}else{
			System.out.println("Jogo nao solucionavel.\n");
		}

	}
}
