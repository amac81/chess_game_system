package gameboard;

import java.util.List;

public abstract class Piece {
	protected Position position;
	private List<Boolean> possibleMoves;

	public Piece() {
	}

	public Piece(Position position, List<Boolean> possibleMoves) {
		this.position = position;
		this.possibleMoves = possibleMoves;
	}

	public Piece(Position position) {
		this.position = position;
	}

	public Boolean[][] possibleMoves() {

		Boolean moves[][] = null;

		return moves;
	}

	public List<Boolean> possibleMove(Position position) {
		return possibleMoves;
	}

	public Boolean isThereAnyPossibleMove(Position position) {
		return true;
	}

}
