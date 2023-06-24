package application;

import chess.ChessPiece;
import chess.enums.Color;

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
			if(piece.getColor() == Color.BLACK)
				System.out.print(ConsoleColors.RED + piece + ConsoleColors.RESET );
			else
				System.out.print(ConsoleColors.GREEN + piece + ConsoleColors.RESET );
		}
		System.out.print(" ");// to separate pieces

	}

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static void testTextColors() {
		System.out.println(ConsoleColors.RED + "RED COLORED" + ConsoleColors.RESET + " NORMAL");
	}

}
