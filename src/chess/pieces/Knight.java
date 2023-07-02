package chess.pieces;

import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;
import gameboard.Position;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece)getBoard().piece(position);
		
		//empty position Or opposite piece
		return piece == null || piece.getColor() != getColor();
	}
	
	
	// the Knight moves similar to King, but in a 2-1 movement logic (example: 2 lines above, 1 column right)		
	@Override
	public 	boolean[][] possibleMoves() {
		//initially, when created, the matrix has all positions with the value False		
		boolean [][] auxMat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
			
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return auxMat;
	}

	@Override
	public String toString() {
		return "N";	
	}
	
}
