package chess.pieces;

import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;

public class Knight extends ChessPiece{

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void increaseMoveCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseMoveCount() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public String toString() {
		return "K";	
	}

	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
