package chess.pieces;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;
import gameboard.Position;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);

		// empty position Or opposite piece
		return piece == null || piece.getColor() != getColor();
	}

	// the King can move one position at once, in every directions (8)
	@Override
	public boolean[][] possibleMoves() {
		// initially, when created, the matrix has all positions with the value False
		boolean[][] auxMat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		// Above Left (NW)
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		// Above Right (NE)
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		// Below Left (SW)
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		// Bellow Right (SE)
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			auxMat[p.getRow()][p.getColumn()] = true;
		}

		// #Special move Castling King Side rook
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// #Special Move Castling king side Rook
			// Rook: 3 columns to right
			Position posRook1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(posRook1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					auxMat[position.getRow()][position.getColumn() + 2] = true;
				}

			}
			
			// #Special Move Castling Queen Side Rook
			// Rook: 4 columns to left
			Position posRook2 = new Position(position.getRow(), position.getColumn() - 3);
			if (testRookCastling(posRook1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					auxMat[position.getRow()][position.getColumn() - 2] = true;
				}

			}
		}

		return auxMat;
	}

	private boolean testRookCastling(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);

		return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
	}

	@Override
	public String toString() {
		return "K";
	}

}
