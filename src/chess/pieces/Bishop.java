package chess.pieces;

import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;
import gameboard.Position;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}


	/*
	 * the bishop can move diagonally as long as there are free positions or even if
	 * it finds an opponent piece there (which it can capture)
	 * 
	 */
	@Override
	public boolean[][] possibleMoves() {
		// initially, when created, the matrix has all positions with the value False
		boolean[][] auxMat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		//Above Left (NW)
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		//opponent piece
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}
		
		//Above Right (NE)
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		//opponent piece
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		//Below Left (SW)
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);	
		}
		//opponent piece
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		//Bellow Right (SE)
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);		
		}	
		//opponent piece
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		return auxMat;
	}
	
	@Override
	public String toString() {
		return "B";
	}
}
