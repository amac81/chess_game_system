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

	// the rook can move in a straight line, horizontally and vertically free
	// positions, including positions where there is an opponent's piece (the rook can capture it)
	// 
	// to the free position, before the allied piece (of the same type), not including the position of this
	@Override
	public boolean[][] possibleMoves() {
		// initially, when created, the matrix has all positions with the value False

		boolean[][] auxMat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		return auxMat;
	}

}
