package gameboard;

import java.util.ArrayList;
import java.util.List;

/* OOP Topics:
 * - Associations
 * - Encapsulation / Access modifiers
 * 
 * Data Structures Topics:
 * - Matrix
 */

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;


	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
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
