package application;

import chess.ChessPiece;

/*
 * This UserInterface class:
 * gets the game pieces matrix
 * print the game board
*/

public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
		int limit = pieces.length; // square matrix: same number of rows and columns

		for (int i = 0; i < limit; i++) {
			System.out.print(limit - i + " ");
			for (int j = 0; j < limit; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");
	}

	public static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");// to separate pieces

	}

}
