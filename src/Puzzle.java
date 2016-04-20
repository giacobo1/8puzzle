import java.io.*;
import java.util.*;
//import java.util.ArrayList;

class Puzzle{

	public static void main(String[]args)
	{	
		Board tabuleiro= new Board();
		//String entrada = new String();
		
		//BufferedReader dado;
		//entrada=args[0];
		// throws Exception;
	
		try{
			BufferedReader dado = new BufferedReader(new FileReader(args[0]));

			int i, j;
			char temp;
			for (i=0;i<3;i++) {
				for (j=0;j<3;j++) {
					temp=(char)dado.read();
					//System.out.println(temp);
					tabuleiro.setBoard(i, j, temp);
				}
			}

			if(tabuleiro.isSolvable()){
				System.out.println("Jogo ok.");
			}else
				System.out.println("Jogo insoluvel.");

		}catch(Exception e)
		{			
			System.out.println("Erro de execução:\n" + e);
		}

	}
}
