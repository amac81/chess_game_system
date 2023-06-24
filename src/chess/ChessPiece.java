package chess;

import chess.enums.Color;
import gameboard.Board;
import gameboard.Piece;

public abstract class ChessPiece extends Piece {
	private Color color;
	private int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public ChessPosition getChessPosition() {
		return null;
	}

	public abstract boolean isThereOpponentPiece();

	public abstract void increaseMoveCount();

	public abstract void decreaseMoveCount();
}