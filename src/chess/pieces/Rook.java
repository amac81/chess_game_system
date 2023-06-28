package chess.pieces;

import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;

/* OOP Topics (for all classes that extends ChessPiece):
 *
 * - Inheritance
 * - Overriding
 * - Polymorphism (ToString)
 * 
 */

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
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
		return "R";
	}

	@Override
	public 	boolean[][] possibleMoves() {
		//initially, when created, the matrix has all positions with the value False
		
		boolean [][] auxMat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		return auxMat;
	}


}
