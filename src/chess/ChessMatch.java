package chess;

import java.util.List;

import chess.enums.Color;
import chess.pieces.King;
import chess.pieces.Queen;
import chess.pieces.Rook;
import gameboard.Board;
import gameboard.Position;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private Boolean check;
	private Boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;

	public ChessMatch() {// it is this class that knows the size of the board
		board = new Board(8, 8);
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

	
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position (1, 2));
		board.placePiece(new King(board, Color.BLACK), new Position (2, 2));
		board.placePiece(new Queen(board, Color.WHITE), new Position (3, 4));
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

	public void setEnPassantVulnerable(ChessPiece enPassantVulnerable) {
		this.enPassantVulnerable = enPassantVulnerable;
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
		
		return mat; //returns the array of pieces from my chess match
	}

	public List<Boolean> possibleMove(ChessPosition sourcePosition) {
		return null;
	}

	public ChessPiece performeChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		return null;
	}

	public ChessPiece replacePromotedPiece(String type) {
		return null;
	}

}
