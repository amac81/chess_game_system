package chess;

import chess.enums.Color;
import gameboard.Board;
import gameboard.Piece;
import gameboard.Position;

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
		return ChessPosition.fromPosition(position);
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece piece = (ChessPiece)getBoard().piece(position);
		//non-null piece and different color from the current piece
		return piece != null && piece.getColor() != color;
	}

	protected void increaseMoveCount() {
		
	}

	protected void decreaseMoveCount() {
		
	}
}