package chess;

import java.security.InvalidParameterException;

/* Data Structures Topics:
* - List
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.enums.Color;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;
import gameboard.Board;
import gameboard.Piece;
import gameboard.Position;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {// it is this class that knows the size of the board
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE; // the player with the white pieces makes the first move
		initialSetup();
	}

	public ChessMatch(int turn, Color currentPlayer, Boolean check, Boolean checkMate, ChessPiece enPassantVulnerable,
			ChessPiece promoted) {
		this.turn = turn;
		this.currentPlayer = currentPlayer;
		this.check = check;
		this.checkMate = checkMate;
		this.enPassantVulnerable = enPassantVulnerable;
		this.promoted = promoted;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toMatrixPosition());
		piecesOnTheBoard.add(piece);
	}

	public ChessPiece performeChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// convert chess positions to matrix positions
		Position source = sourcePosition.toMatrixPosition();
		Position target = targetPosition.toMatrixPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);

		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You cannot put yourself in a check position!");
		}

		ChessPiece movedPiece = (ChessPiece) board.piece(target);

		// #special_move Promotion
		promoted = null;

		if (movedPiece instanceof Pawn) {
			if (movedPiece.getColor() == Color.WHITE && target.getRow() == 0
					|| movedPiece.getColor() == Color.BLACK && target.getRow() == 7) {
				promoted = (ChessPiece) board.piece(target);
				promoted = replacePromotedPiece("Q");// Queen by default
			}
		}

		// check opponent check after currentPlayer successful move
		// and sign this in the check attribute of the ChessMatch
		check = testCheck(opponent(currentPlayer)) ? true : false;

		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		} else {
			nextTurn();
		}

		// #special_move En Passant
		if (movedPiece instanceof Pawn
				&& (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
			enPassantVulnerable = movedPiece;
		} else {
			enPassantVulnerable = null;
		}

		// down cast from Piece(matrix) to ChessPiece
		return (ChessPiece) capturedPiece;
	}

	private Piece makeMove(Position source, Position target) {
		ChessPiece piece = (ChessPiece) board.removePiece(source);
		piece.increaseMoveCount();

		Piece capturedPiece = board.removePiece(target);
		board.placePiece(piece, target);
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		// #special_move Castling King side Rook
		if (piece instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceRookPosition = new Position(source.getRow(), source.getColumn() + 3);
			Position targetRookPosition = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(sourceRookPosition);
			board.placePiece(rook, targetRookPosition);
			rook.increaseMoveCount();
		}

		// #special_move Castling Queen side Rook
		if (piece instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceRookPosition = new Position(source.getRow(), source.getColumn() - 4);
			Position targetRookPosition = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(sourceRookPosition);
			board.placePiece(rook, targetRookPosition);
			rook.increaseMoveCount();
		}

		// #special_move En Passant
		if (piece instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {
				Position pawnPosition;
				if (piece.getColor() == Color.WHITE) {
					pawnPosition = new Position(target.getRow() + 1, target.getColumn());
				} else {
					pawnPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}

		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece piece = (ChessPiece) board.removePiece(target);
		piece.decreaseMoveCount();

		board.placePiece(piece, source);
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}

		// UNDO #special_move Castling King side Rook
		if (piece instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceRookPosition = new Position(source.getRow(), source.getColumn() + 3);
			Position targetRookPosition = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(targetRookPosition);
			board.placePiece(rook, sourceRookPosition);
			rook.decreaseMoveCount();
		}

		// UNDO #special_move Castling Queen side Rook
		if (piece instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceRookPosition = new Position(source.getRow(), source.getColumn() - 4);
			Position targetRookPosition = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(targetRookPosition);
			board.placePiece(rook, sourceRookPosition);
			rook.decreaseMoveCount();
		}

		// UNDO #special_move En Passant
		if (piece instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				ChessPiece pawn = (ChessPiece) board.removePiece(target);

				Position pawnPosition;
				if (piece.getColor() == Color.WHITE) {
					pawnPosition = new Position(3, target.getColumn());
				} else {
					pawnPosition = new Position(4, target.getColumn());
				}

				board.placePiece(pawn, pawnPosition);
			}
		}
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position!");
		}

		// player tries to move a piece that is not his
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece isn't yours!");
		}

		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move for the chosen piece!");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position!");
		}
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		// 1st convert a Chess Position to a matrix Position
		Position position = sourcePosition.toMatrixPosition();
		validateSourcePosition(position);

		return board.piece(position).possibleMoves();
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(p -> ((ChessPiece) p).getColor() == color)
				.collect(Collectors.toList());

		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}

	// validate that for all the opponent's pieces,
	// if there is a possible move that will reach the king's position
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toMatrixPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(p -> ((ChessPiece) p).getColor() == opponent(color)).collect(Collectors.toList());

		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true; // the king is in check position
			}
		}
		return false;
	}

	/*
	 * the king is in check mate position when it is in check and there is no
	 * possible move, by a piece of its color, that takes it out of the check
	 * situation
	 */
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}

		List<Piece> myPieces = piecesOnTheBoard.stream().filter(p -> ((ChessPiece) p).getColor() == color)
				.collect(Collectors.toList());

		for (Piece p : myPieces) {
			boolean[][] mat = p.possibleMoves();
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece) p).getChessPosition().toMatrixPosition();
						Position target = new Position(j, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}

		return true;// the king is in Check Mate position
	}

	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}

	public Board getBoard() {
		return board;
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public Boolean getCheck() {
		return check;
	}

	public Boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public ChessPiece getPromoted() {
		return promoted;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// downcast to ChessPiece
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}

		return mat; // returns the array of pieces from my chess match
	}

	public ChessPiece replacePromotedPiece(String type) {

		if (promoted == null) {
			throw new IllegalStateException("There is no piece to be promoted!");
		}

		// use of method equals for String compare. String is a Class, not a primitive
		// type
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
			throw new InvalidParameterException("Invalid type of piece for promotion");
		}

		Position pos = promoted.getChessPosition().toMatrixPosition();
		Piece p = board.removePiece(pos);

		piecesOnTheBoard.remove(p);

		ChessPiece newPiece = newPiece(type, promoted.getColor());

		board.placePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);

		return newPiece;
	}

	private ChessPiece newPiece(String type, Color color) {
		if (type.equals("Q")) {
			return new Queen(board, color);
		}
		if (type.equals("N")) {
			return new Knight(board, color);
		}
		if (type.equals("B")) {
			return new Bishop(board, color);
		}
		return new Rook(board, color);
	}

}
