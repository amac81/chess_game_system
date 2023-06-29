package chess.pieces;

import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;
import gameboard.Position;

public class King extends ChessPiece {
	
	

	public King(Board board, Color color) {
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

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece)getBoard().piece(position);
		
		//empty position Or opposite piece
		return piece == null || piece.getColor() != getColor();
	}
	
	
	// the King can move one position at once, in every directions (8)		
	@Override
	public 	boolean[][] possibleMoves() {
		//initially, when created, the matrix has all positions with the value False		
		boolean [][] auxMat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		
		//above
		p.setValues(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		//below
		p.setValues(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		//left
		p.setValues(position.getRow(), position.getColumn() - 1 );
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		//right
		p.setValues(position.getRow(), position.getColumn() + 1 );
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		//Above Left (NO)
		p.setValues(position.getRow() - 1, position.getColumn() - 1 );
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		//Above Right (NE)
		p.setValues(position.getRow() - 1, position.getColumn() + 1 );
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		//Below Left (SO)
		p.setValues(position.getRow() + 1, position.getColumn() - 1 );
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		//Bellow Right (SE)
		p.setValues(position.getRow() + 1, position.getColumn() + 1 );
		if(getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		return auxMat;
	}

}
