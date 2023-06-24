package chess;

import java.util.ArrayList;
import java.util.List;

import chess.enums.Color;
import gameboard.Board;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private Boolean check;
	private Boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;

	public ChessMatch() {
		board = new Board();
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

	public List<ChessPiece> getPieces() {
		return null;
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
