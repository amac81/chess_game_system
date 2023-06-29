package chess.pieces;

import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;
import gameboard.Position;

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
		
		Position p = new Position(0, 0);
		
		//above
		p.setValues(position.getRow() - 1, position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);			
		}
		//opponent piece
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		//left
		p.setValues(position.getRow(), position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);			
		}
		//opponent piece
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		//right
		p.setValues(position.getRow(), position.getColumn() + 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);			
		}
		//opponent piece
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		//below
		p.setValues(position.getRow() + 1, position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);			
		}	
		//opponent piece
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		return auxMat;
	}

}
