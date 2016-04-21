import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) {	
	
		Board board = new Board(args[0]);

		//board.printBoard();

			
		Vector<Board> boardList = board.generateNextBoard(board.findWhiteTile());

		for (Board b: boardList) {
			b.printBoard();
			System.out.format("\n");
		}

	}
}
