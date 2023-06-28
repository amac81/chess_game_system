package chess.pieces;

import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;

public class King extends ChessPiece {
	
	

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isThereOpponentPiece() {
		// TODO Auto-generated method stub
		return false;
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
	public 	boolean[][] possibleMoves() {
		//initially, when created, the matrix has all positions with the value False
		
		boolean [][] auxMat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		return auxMat;
	}

}
