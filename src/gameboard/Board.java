package gameboard;

import java.util.List;

public class Board {
	private int rows;
	private int columns;
	private List <Piece> pieces;

	public Board(int rows, int columns, List<Piece> pieces) {
		this.rows = rows;
		this.columns = columns;
		this.pieces = pieces;
	}

	public Piece piece(int row, int column) {
		return null;
	}

	public Piece piece(Position position) {
		return null;
	}

	public void placePiece(Piece piece, Position position) {

	}

	public Piece removePiece(Position position) {
		return null;
	}

	public Boolean positionExists(Position position) {
		return false;
	}

	public Boolean thereIsAPiece(Position position) {
		return false;
	}
}