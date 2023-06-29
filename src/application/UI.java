package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.enums.Color;

/*
 * This UserInterface class:
 * gets the game pieces matrix
 * print the game board


 OOP Topics:
 * method override printPiece()
 */

public class UI {

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void printMatch(ChessMatch chessMatch, List <ChessPiece> capturedPieces) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		System.out.println("Waiting Player:" + chessMatch.getCurrentPlayer());
		printCapturedPieces(capturedPieces);
	}

	private static void printBoard(ChessPiece[][] pieces) {
		int limit = pieces.length; // square matrix: same number of rows and columns

		for (int i = 0; i < limit; i++) {
			System.out.print(limit - i + " ");
			for (int j = 0; j < limit; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");
	}

	public static void printBoard(ChessPiece[][] pieces, boolean possibleMoves[][]) {
		int limit = pieces.length; // square matrix: same number of rows and columns

		for (int i = 0; i < limit; i++) {
			System.out.print(limit - i + " ");
			for (int j = 0; j < limit; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean possibleMove) {
		if (possibleMove) {
			System.out.print(ConsoleColors.ANSI_GREEN_BACKGROUND);
		}

		if (piece == null) {
			System.out.print("-" + ConsoleColors.ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ConsoleColors.ANSI_WHITE + piece + ConsoleColors.ANSI_RESET);
			} else {
				System.out.print(ConsoleColors.ANSI_YELLOW + piece + ConsoleColors.ANSI_RESET);
			}
		}

		System.out.print(" ");// to separate pieces
	}

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String str = sc.nextLine();
			char column = str.charAt(0);
			int row = Integer.parseInt(str.substring(1));
			return new ChessPosition(column, row);

		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valide values are from a1 to h8!");
		}
	}
	
	private static void printCapturedPieces(List<ChessPiece> capturedPieces) {
		List<ChessPiece> whitePieces = capturedPieces.stream().filter(p -> p.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> blackPieces = capturedPieces.stream().filter(p -> p.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("\nCaptured Pieces:");
		System.out.print(ConsoleColors.ANSI_WHITE);
		System.out.print("White:");
		System.out.print(Arrays.toString(whitePieces.toArray()));
		System.out.print(ConsoleColors.ANSI_RESET);
	
		System.out.print(ConsoleColors.ANSI_YELLOW);
		System.out.print("\nBlack:");
		System.out.print(Arrays.toString(blackPieces.toArray()));
		System.out.print(ConsoleColors.ANSI_RESET);

		
	}

}
