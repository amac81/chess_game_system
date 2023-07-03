package chess.pieces;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;
import gameboard.Board;
import gameboard.Position;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
		// TODO Auto-generated constructor stub
	}

	/*
	 * the pawn can move in a straight line only one position at a time. If it's the
	 * first time it move, it can move 2 positions
	 *
	 * if there is an opponent piece one position away diagonally the pawn can move
	 * diagonally and capture that piece
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] auxMat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			// straight 1 move
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				auxMat[p.getRow()][p.getColumn()] = true;
			}
			// first move 2 positions move
			p.setValues(position.getRow() - 2, position.getColumn());
			// position before
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0
					&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				auxMat[p.getRow()][p.getColumn()] = true;
			}
			// diagonal moves
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				auxMat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				auxMat[p.getRow()][p.getColumn()] = true;
			}

			// #special_move En Passant WHITE piece
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					auxMat[left.getRow() - 1 ][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					auxMat[right.getRow() - 1 ][right.getColumn()] = true;
				}
			}

		} else // BLACK piece
		{
			// straight 1 move
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				auxMat[p.getRow()][p.getColumn()] = true;
			}
			// first move 2 positions move
			p.setValues(position.getRow() + 2, position.getColumn());
			// position before
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0
					&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				auxMat[p.getRow()][p.getColumn()] = true;
			}
			// diagonal moves
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				auxMat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				auxMat[p.getRow()][p.getColumn()] = true;
			}

			// #special_move En Passant BLACK piece
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					auxMat[left.getRow() + 1 ][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					auxMat[right.getRow() + 1 ][right.getColumn()] = true;
				}
			}
		}

		return auxMat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
