package application;

import chess.ChessMatch;

public class Program {
	public static void main(String[] args) {
	
		

		ChessMatch chessMatch = new ChessMatch();
		
		
		//UI.testTextColors();
	
		//UI.clearScreen();
		UI.printBoard(chessMatch.getPieces());
		
		
	}

}
